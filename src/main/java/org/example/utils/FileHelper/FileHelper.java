package org.example.utils.FileHelper;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FileHelper {
    protected static final Logger logger = LogManager.getLogger();

    // Create new certificates
    public void createCertificates() {
        logger.info("Create a new certificates to apply for https request...");

        try {
            // Create a new trust manager that trusts all certificates
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };

            // Activate the new trust manager
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    // [File] Download file
    public void downloadFile(String urlDownload, String saveFolderPath) {
        // Trusts all certificates
        createCertificates();

        logger.info("Downloading file from url `{}`", urlDownload);
        String fileName = urlDownload.substring(urlDownload.lastIndexOf("/") + 1);

        try {
            URL url = new URI(urlDownload).toURL();
            URLConnection connection = url.openConnection();

            FileUtils.copyURLToFile(connection.getURL(), new File(saveFolderPath + fileName));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    // [File] Check file exists in folder
    public boolean checkFileExists(String fileName, String folderPath) {
        File directory = new File(folderPath);
        String[] filesList = directory.list();

        int flag = 0;
        if (filesList != null) {
            for (String file_name : filesList) {
                if (file_name.equalsIgnoreCase(fileName)) {
                    logger.info("File `{}` is existed in folder `{}`...", fileName, folderPath);
                    flag = 1;
                }
            }
        } else {
            logger.info("No file is existed in folder");
            return false;
        }

        if (flag == 0) {
            logger.info("File `{}` is not existed in folder `{}`", fileName, folderPath);
            return false;
        }

        return true;
    }

    // Copy file from this folder to that folder
    public void copyFileToFolder(String fileName, String sourceFolder, String destinationFolder) {
        logger.info("Copying file `{}` to folder `{}`", fileName, destinationFolder);

        File sourceFile = new File(sourceFolder + fileName);
        File destFile = new File(destinationFolder + "/" + fileName);

        try {
            FileUtils.copyFile(sourceFile, destFile);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // Copy all file from this folder to that folder
    public void copyAllFileToFolder(String sourceFolder, String destinationFolder) {
        logger.info("Copying all file from folder `{}` to `{}` ....", sourceFolder, destinationFolder);

        // Source and destination directories
        Path sourceDir = Paths.get(sourceFolder);
        Path destinationDir = Paths.get(destinationFolder);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourceDir)) {
            for (Path file : stream) {
                Path destinationFile = destinationDir.resolve(file.getFileName());
                Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // [File] Rename file
    public void renameFile(String filePath, String oldFileName, String newFileName) {
        logger.info("Renaming file `{}` to `{}`", oldFileName, newFileName);

        File oldFile = new File(filePath + oldFileName);
        File newFile = new File(filePath + newFileName);

        if (oldFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                logger.info("File renamed successfully from `{}` to `{}`", oldFileName, newFileName);
            } else {
                logger.error("Failed to rename file `{}` to `{}`", oldFileName, newFileName);
            }
        } else {
            logger.error("File `{}` does not exist", oldFileName);
        }
    }

    // [File] Delete a file in folder
    public void deleteFile(String fileName, String folderPath) {
        logger.info("Deleting file `{}` from folder `{}`", fileName, folderPath);

        File fileToDelete = new File(folderPath + File.separator + fileName);

        if (fileToDelete.exists()) {
            if (fileToDelete.delete()) {
                logger.info("File `{}` deleted successfully...", fileName);
            } else {
                logger.error("Failed to delete file `{}`", fileName);
            }
        } else {
            logger.error("File `{}` does not exist in folder `{}`", fileName, folderPath);
        }
    }

    // [Zip] Zip file and file in subdirectories
    public void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    // [Zip] Zip a file
    public void zipAFile(String sourceFile, String destinationFile) {
        logger.info("Zipping a file `{}` to zip file `{}` ....", sourceFile, destinationFile);

        int length;
        try {
            FileOutputStream fos = new FileOutputStream(destinationFile);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            File fileToZip = new File(sourceFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }

            zipOut.close();
            fis.close();
            fos.close();
            logger.info("Zipping a file is successfully !");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // [Zip] Zip a folder
    public void zipAFolder(String sourceFolder, String destinationFile) {
        logger.info("Zipping a folder `{}` to zip file `{}` ....", sourceFolder, destinationFile);

        try {
            FileOutputStream fos = new FileOutputStream(destinationFile);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            File fileToZip = new File(sourceFolder);
            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();

            logger.info("Zipping a folder is successfully !");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // [Unzip] Unzip file *.zip
    public void unZip(String zipFilePath, String destinationDir) {
        Path zipFile = Paths.get(zipFilePath);
        Path destDirPath = Paths.get(destinationDir);

        // Open the ZIP file
        try {
            // Create the destination directory if it doesn't exist
            if (Files.notExists(destDirPath)) {
                Files.createDirectories(destDirPath);
            }

            ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile));
            ZipEntry zipEntry;
            byte[] buffer = new byte[1024];

            // Read each entry in the ZIP file
            while ((zipEntry = zis.getNextEntry()) != null) {
                Path destinationFile = destDirPath.resolve(zipEntry.getName());

                // Handle directories
                if (zipEntry.isDirectory()) {
                    Files.createDirectories(destinationFile);
                } else {
                    // Ensure parent directories exist
                    if (Files.notExists(destinationFile.getParent())) {
                        Files.createDirectories(destinationFile.getParent());
                    }

                    // Write the file content to the destination directory
                    try (OutputStream os = Files.newOutputStream(destinationFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            os.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
