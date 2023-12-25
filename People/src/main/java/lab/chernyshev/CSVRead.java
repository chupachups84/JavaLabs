package lab.chernyshev;


import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVRead {
    public static List<Person> Read(String csvFilePath, char separator) throws IOException {
        int departmentId = 1;
        List<Person> people = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        boolean flag = false;
        try (InputStream in = CSVRead.class.getClassLoader().getResourceAsStream(csvFilePath);
             com.opencsv.CSVReader reader = in == null ? null :
                     new CSVReaderBuilder(new InputStreamReader(in))
                             .withCSVParser(new CSVParserBuilder()
                                     .withSeparator(separator)
                                     .build())
                             .build()) {
            if (reader == null) {
                throw new FileNotFoundException(csvFilePath);
            }
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (!flag) {
                    flag = true;
                } else {
                    Person person = new Person(
                            Integer.parseInt(nextLine[0]),
                            String.valueOf(nextLine[1]),
                            String.valueOf(nextLine[2]),
                            new Department(
                                    departmentId++,
                                    String.valueOf(nextLine[4])),
                            Integer.parseInt(nextLine[5]),
                            formatter.parse(nextLine[3]
                            )
                    );
                    people.add(person);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return people;
    }
}
