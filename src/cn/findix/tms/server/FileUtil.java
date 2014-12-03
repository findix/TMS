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

}
