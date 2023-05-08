package org.casadocodigo.store.infra;

import org.casadocodigo.store.infra.aws.Credenciais;
import org.casadocodigo.store.infra.aws.OperacoesS3;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

@RequestScoped
public class FileSaver {
    @Inject
    private HttpServletRequest request;
    private static final String CONTENT_DISPOSITION = "content-disposition";
    private static final String FILENAME_KEY = "filename=";

    public String write(String baseFolder, Part multipartFile) {
        OperacoesS3 s3 = new OperacoesS3(Credenciais.ACCESS_KEY, Credenciais.SECRET_KEY);
        String fileName = extractFilename(multipartFile.getHeader(CONTENT_DISPOSITION));


        s3.criarBucket(baseFolder);
        s3.listarBuckets().forEach(System.out::println);
        s3.enviarArquivo(baseFolder, "teste_aws.txt", "D:\\teste_aws.txt");
        s3.listarArquivos(baseFolder);
//            Arrumar depois
        return "https://s3.console.aws.amazon.com/s3/object/" + baseFolder + "?region=sa-east-1&prefix=" + fileName;
    }

    private String extractFilename(String contentDisposition) {
        if (contentDisposition == null) {
            return null;
        }
        int startIndex = contentDisposition.indexOf(FILENAME_KEY);
        if (startIndex == -1) {
            return null;
        }
        String filename = contentDisposition.substring(startIndex + FILENAME_KEY.length());
        if (filename.startsWith("\"")) {
            int endIndex = filename.indexOf("\"", 1);
            if (endIndex != -1) {
                return filename.substring(1, endIndex);
            }
        } else {
            int endIndex = filename.indexOf(";");
            if (endIndex != -1) {
                return filename.substring(0, endIndex);
            }
        }
        return filename;
    }

}