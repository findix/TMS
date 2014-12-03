package cn.findix.tms.server;

import java.io.*;

/**
 * Created by Sean on 2014/12/3.
 */
public class FileUtil {

    /**
     * 根据源路径名和目标路径名复制文件。
     *
     * @param source_name 源路径名
     * @param dest_name   目标路径名
     * @param overwrite   值为判断如果目标路径存在是否覆盖，1为覆盖旧的文件，2为不覆盖，操作取消。
     * @return 当复制成功时返回1， 当目标文件存在且type值为2时返回2，其他情况返回0
     * @throws IOException 发生I/O错误
     */
    public static int copyFile(String source_name, String dest_name, boolean overwrite) throws IOException {
        int result = 0;
        int byte_read;
        byte[] buffer;
        File source_file = new File(source_name);
        File dest_file = new File(dest_name);
        FileInputStream source = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileOutputStream dest = null;
        try {
            if (!source_file.exists() || !source_file.isFile()) //不存在
                throw new RuntimeException("FileCopy: no such source file:" + source_name);
            if (!source_file.canRead()) //不可读
                throw new RuntimeException("FileCopy: source file" + source_name
                        + "is unreadable");
            if (dest_file.exists()) {
                if (dest_file.isFile()) {
                    if (overwrite) {
                        dest_file.delete(); //覆盖
                        result = 1;
                    } else {
                        result = 2;
                        return result; //不覆盖 ，程序返回。
                    }
                } else
                    throw new RuntimeException("FileCopy: destination" + dest_name
                            + "is not a file.");
            } else {
                File parentDir = new File(dest_file.getParent()); //获得目录信息
                if (!parentDir.exists()) {
                    throw new RuntimeException("FileCopy: destination" + dest_name
                            + "directory doesn't exist."); //目录不存在
                }
                if (!parentDir.canWrite())
                    throw new RuntimeException("FileCopy: destination" + dest_name
                            + "is unwriteable."); //目录不可写
            }

//开始复制文件
//输入流
            source = new FileInputStream(source_file);
            bis = new BufferedInputStream(source);
//输出流
            dest = new FileOutputStream(dest_file);
            bos = new BufferedOutputStream(dest);
            buffer = new byte[1024 * 5];
            while ((byte_read = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, byte_read);
            }
            result = 1;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (source != null) {
                bis.close();
                source.close();
            }
            if (dest != null) {
                bos.flush();
                bos.close();
                dest.flush();
                dest.close();
            }
        }
        return result;

    }


    /**根据源路径名和目标路径名复制目录。
     * 复制目录以复制文件为基础，通过递归复制子目录实现。
     * @param source_name 源路径名
     * @param dest_name 目标路径名
     * @param type 值为判断如果目标路径存在是否覆盖，1为覆盖旧的目录，2为不覆盖，操作取消。
     * @return 当复制成功时返回1， 当目标目录存在且type值为2时返回2，其他情况返回0
     * @throws IOException 发生I/O错误
     */
    public static int copyDirectory(String source_name, String dest_name, boolean type) throws IOException {
        File source_file = new File(source_name);
        File dest_file = new File(dest_name);
        int result = 0;
        if (!source_file.exists() || source_file.isFile()) //不存在
            throw new RuntimeException("DirCopy: no such dir" + source_name);
        if (!source_file.canRead()) //不可读
            throw new RuntimeException("DirCopy: source file" + source_name
                    + "is unreadable");
        if (dest_file.exists()) {
            if (type) {
                deleteDir(dest_name); //覆盖
                result = 1;
            }
            if (!type) {
                result = 2; //不覆盖
                return result;
            }
        }
        if (!dest_file.exists()) {
            new File(dest_name).mkdirs(); //创建目标目录
            File[] fileList = source_file.listFiles();
            assert fileList != null;
            for (File aFileList : fileList) {
                System.out.println(aFileList.getName());
                if (aFileList.isFile()) {
                    //用copyFile函数复制文件
                    copyFile(
                            source_name + "/" + aFileList.getName(),
                            dest_name + "/" + aFileList.getName(),
                            type);
                } else if (aFileList.isDirectory()) {
                    //递归
                    copyDirectory(
                            source_name + "/" + aFileList.getName(),
                            dest_name + "/" + aFileList.getName(), type);
                }
            }
            result = 1;

        }

        return result;
    }

    /**
     * 删除路径名所代表的文件。
     *
     * @param filePath 路径名
     * @return 当文件成功删除，返回true,否则返回false.
     */
    public static boolean deleteFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
//System.out.println(filePath+"文件已删除.");
            return true;
        } else {
//System.out.println("逻辑错误："+filePath+"文件不存在.");
            return false;
        }
    }

    /**
     * 删除路径名所代表的目录
     *
     * @param filePath 路径名
     * @return 当目录成功删除，返回true,否则返回false.
     */
    public static boolean deleteDir(String filePath) throws IOException {
        boolean isDone = false;
        File file = new File(filePath);
//判断是文件还是目录
        if (file.exists() && file.isDirectory()) {
            if (file.listFiles().length == 0) {
                file.delete();
                isDone = true;
            } else {
                File[] delFiles = file.listFiles();
                for (int i = 0; i < delFiles.length; i++) {
                    if (delFiles[i].isDirectory()) {
                        deleteDir(delFiles[i].getAbsolutePath()); //递归调用deleteDir函数
                    } else {
                        delFiles[i].delete();
                    }
                }
            }
//删除最后剩下的目录名。
            deleteDir(filePath);
            isDone = true;
        } else
            return false;
        return isDone;
    }

    /**
     * 根据路径名创建目录
     * @param filePath 路径名
     * @return 当创建目录成功后，返回true,否则返回false.
     */
    public static boolean createDir(String filePath) {
        boolean isDone = false;
        File file = new File(filePath);
        if(file.exists())
            throw new RuntimeException("FileDirectory: "+filePath+" is already exist");
        isDone = file.mkdirs();
        return isDone;
    }
}

