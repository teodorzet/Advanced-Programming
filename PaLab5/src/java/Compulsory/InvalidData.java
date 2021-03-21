package Compulsory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

class InvalidDataException extends Exception{
    InvalidDataException(String s){
        super(s);
    }
}

class TestInvalidData{

}
