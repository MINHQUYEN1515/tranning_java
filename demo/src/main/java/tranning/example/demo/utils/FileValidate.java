package tranning.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileValidate implements ConstraintValidator<FileValid, MultipartFile> {
    @Override
    public void initialize(FileValid imageConstraint) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return false;
        }
        String getType = file.getContentType();
        if (!isSupportContentFiled(getType)) {
            return false;
        }
        return true;
    }

    private boolean isSupportContentFiled(String type) {
        return type.equals("image/png") || type.equals("image/jpg") || type.equals("image/jpeg");
    }

}
