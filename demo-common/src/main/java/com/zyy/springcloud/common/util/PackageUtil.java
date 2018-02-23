package com.zyy.springcloud.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;


public class PackageUtil {
    private static final Logger logger = LoggerFactory.getLogger(PackageUtil.class);

    /**
     * 获取包下的所有class
     *
     * @param packageName 包名
     * @return 类集合
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getClass(String packageName) throws ClassNotFoundException {
        List<String> classNameList = getClassName(packageName);
        if (classNameList != null) {
            List<Class<?>> classList = new ArrayList<>();
            for (String className : classNameList) {
                classList.add(Class.forName(className));
            }
            return classList;
        }
        return null;
    }

    /**
     * 根据正则表达获取包下的所有class
     *
     * @param packageName 包名
     * @param pattern     正则表达式
     * @return 类集合
     * @throws ClassNotFoundException
     */
    public static List<Class<?>> getClass(String packageName, String pattern) throws ClassNotFoundException {
        List<String> classNameList = getClassName(packageName);
        if (classNameList != null) {
            List<Class<?>> classList = new ArrayList<>();
            for (String className : classNameList) {
                if (className.matches(pattern)) {
                    classList.add(Class.forName(className));
                }
            }
            return classList;
        }
        return null;
    }

    /**
     * 获取某包下（包括该包的所有子包）所有类
     *
     * @param packageName 包名
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName) {
        return getClassName(packageName, true);
    }

    /**
     * 获取某包下所有类
     *
     * @param packageName  包名
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath(), null, childPackage);
            } else if (type.equals("jar")) {
                String filePath = url.getPath();
                if ("war".equalsIgnoreCase(filePath.substring(filePath.indexOf("war"), filePath.indexOf("!")))) {    //war包
                    fileNames = getClassNameByWar(filePath, childPackage);
                } else {    //jar包
                    fileNames = getClassNameByJar(url.getPath(), childPackage);
                }
            }
        } else {
            fileNames = getClassNameByJars(((URLClassLoader) loader).getURLs(), packagePath, childPackage);
        }
        return fileNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath     文件路径
     * @param className    类名集合
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                if (childPackage) {
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));
                }
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9,
                            childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace("\\", ".");
                    myClassName.add(childFilePath);
                }
            }
        }

        return myClassName;
    }

    /**
     * 从jar获取某包下所有类
     *
     * @param jarPath      jar文件路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    @SuppressWarnings("resource")
    private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        String[] jarInfo = jarPath.split("!");
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));
        String packagePath = jarInfo[1].substring(1);
        try {
            JarFile jarFile = new JarFile(jarFilePath);
            Enumeration<JarEntry> entrys = jarFile.entries();
            while (entrys.hasMoreElements()) {
                JarEntry jarEntry = entrys.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    if (childPackage) {
                        if (entryName.startsWith(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    } else {
                        int index = entryName.lastIndexOf("/");
                        String myPackagePath;
                        if (index != -1) {
                            myPackagePath = entryName.substring(0, index);
                        } else {
                            myPackagePath = entryName;
                        }
                        if (myPackagePath.equals(packagePath)) {
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                            myClassName.add(entryName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("解析jar包异常", e);
        }
        return myClassName;
    }

    /**
     * 从所有jar中搜索该包，并获取该包下所有类
     *
     * @param urls         URL集合
     * @param packagePath  包路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        if (urls != null) {
            for (int i = 0; i < urls.length; i++) {
                URL url = urls[i];
                String urlPath = url.getPath();
                // 不必搜索classes文件夹
                if (urlPath.endsWith("classes/")) {
                    continue;
                }
                String jarPath = urlPath + "!/" + packagePath;
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));
            }
        }
        return myClassName;
    }

    /**
     * 1. 从war获取某包下所有类
     * 2. 从war中的jar获取某包下的所有类
     *
     * @param warPath      war文件完整路径
     * @param childPackage 是否遍历子包
     * @return 类的完整名称
     */
    @SuppressWarnings("resource")
    private static List<String> getClassNameByWar(String warPath, boolean childPackage) {
        List<String> myClassName = new ArrayList<String>();
        try {
            String[] warInfo = warPath.split("!");
            if (warInfo.length == 3) {    //三级目录war包中含jar包路径
                String warFilePath = warInfo[0].substring(warInfo[0].indexOf("/"));
                String jarFilePath = warInfo[1].substring(1);
                String packagePath = warInfo[2].substring(1);
                JarFile warFile = new JarFile(warFilePath.substring(warFilePath.indexOf("/")));
                Enumeration<JarEntry> entrys = warFile.entries();
                while (entrys.hasMoreElements()) {
                    JarEntry jarEntry = entrys.nextElement();
                    String entryName = jarEntry.getName();
                    if (entryName.endsWith(jarFilePath)) {
                        JarInputStream jarIns = new JarInputStream(warFile.getInputStream(jarEntry));
                        JarEntry jarEntrys = jarIns.getNextJarEntry();
                        while (jarEntrys != null) {
                            JarEntry subJarEntry = jarEntrys;
                            String subEntryName = subJarEntry.getName();
                            if (subEntryName.endsWith(".class")) {
                                if (childPackage) {
                                    if (subEntryName.startsWith(packagePath)) {
                                        subEntryName = subEntryName.replace("/", ".").substring(0, subEntryName.lastIndexOf("."));
                                        myClassName.add(subEntryName);
                                    }
                                } else {
                                    int index = subEntryName.lastIndexOf("/");
                                    String myPackagePath;
                                    if (index != -1) {
                                        myPackagePath = subEntryName.substring(0, index);
                                    } else {
                                        myPackagePath = subEntryName;
                                    }
                                    if (myPackagePath.equals(packagePath)) {
                                        subEntryName = subEntryName.replace("/", ".").substring(0, subEntryName.lastIndexOf("."));
                                        myClassName.add(subEntryName);
                                    }
                                }
                            }
                            jarEntrys = jarIns.getNextJarEntry();
                        }
                    }
                }
            } else if (warInfo.length == 2) {    //二级目录war包+文件
                return getClassNameByJar(warPath, childPackage);
            }
        } catch (IOException e) {
            logger.error("war包解析异常", e);
        }
        return myClassName;
    }

    private static void inputstream2File(InputStream ins, File file) throws IOException {
        OutputStream os = new FileOutputStream(file);
        int len = ins.available();
        if (len < 1024 * 1024) {
            byte[] bytes = new byte[len + 1];
            ins.read(bytes);
            os.write(bytes);
        } else {
            int byteCount = 0;
            //1M逐个读取  
            byte[] bytes = new byte[1024 * 1024];
            while ((byteCount = ins.read(bytes)) != -1) {
                os.write(bytes, 0, byteCount);
            }
        }
        os.flush();
        os.close();
        ins.close();
    }
}
