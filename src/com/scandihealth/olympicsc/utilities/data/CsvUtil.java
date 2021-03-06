package com.scandihealth.olympicsc.utilities.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvUtil implements Serializable {
    // Init ---------------------------------------------------------------------------------------

    // Defaults.
    private static final char DEFAULT_CSV_SEPARATOR = ';';
    private static final String DEFAULT_LINE_SEPARATOR = "\r\n"; // CRLF.

    private CsvUtil() {
        // Utility class, hide the constructor.
    }

    // Parsers ------------------------------------------------------------------------------------

    /**
     * CSV content parser. Convert an InputStream with the CSV contents to a two-dimensional List
     * of Strings representing the rows and columns of the CSV. Each CSV record is expected to be
     * separated by the default CSV field separator, a comma.
     *
     * @param csvInput The InputStream with the CSV contents.
     * @return A two-dimensional List of Strings representing the rows and columns of the CSV.
     */
    public static List<List<String>> parseCsv(InputStream csvInput) {
        return parseCsv(csvInput, DEFAULT_CSV_SEPARATOR);
    }

    /**
     * CSV content parser. Convert an InputStream with the CSV contents to a two-dimensional List
     * of Strings representing the rows and columns of the CSV. Each CSV record is expected to be
     * separated by the specified CSV field separator.
     *
     * @param csvInput     The InputStream with the CSV contents.
     * @param csvSeparator The CSV field separator to be used.
     * @return A two-dimensional List of Strings representing the rows and columns of the CSV.
     */
    public static List<List<String>> parseCsv(InputStream csvInput, char csvSeparator) {

        // Prepare.
        BufferedReader csvReader = null;
        List<List<String>> csvList = new ArrayList<List<String>>();
        String csvRecord = null;

        // Process records.
        try {
            csvReader = new BufferedReader(new InputStreamReader(csvInput, "UTF-8"));
            while ((csvRecord = csvReader.readLine()) != null) {
                csvList.add(parseCsvRecord(csvRecord, csvSeparator));
            }
        } catch (IOException e) {
            throw new RuntimeException("Reading CSV failed.", e);
        } finally {
            if (csvReader != null) try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return csvList;
    }

    /**
     * CSV record parser. Convert a CSV record to a List of Strings representing the fields of the
     * CSV record. The CSV record is expected to be separated by the specified CSV field separator.
     *
     * @param record       The CSV record.
     * @param csvSeparator The CSV field separator to be used.
     * @return A List of Strings representing the fields of each CSV record.
     */
    private static List<String> parseCsvRecord(String record, char csvSeparator) {

        // Prepare.
        boolean quoted = false;
        StringBuilder fieldBuilder = new StringBuilder();
        List<String> fields = new ArrayList<String>();

        // Process fields.
        for (int i = 0; i < record.length(); i++) {
            char c = record.charAt(i);
            fieldBuilder.append(c);

            if (c == '"') {
                quoted = !quoted; // Detect nested quotes.
            }

            if ((!quoted && c == csvSeparator) // The separator ..
                    || i + 1 == record.length()) // .. or, the end of record.
            {
                String field = fieldBuilder.toString() // Obtain the field, ..
                        .replaceAll(csvSeparator + "$", "") // .. trim ending separator, ..
                        .replaceAll("^\"|\"$", "") // .. trim surrounding quotes, ..
                        .replace("\"\"", "\""); // .. and un-escape quotes.
                fields.add(field.trim()); // Add field to List.
                fieldBuilder = new StringBuilder(); // Reset.
            }
        }

        return fields;
    }

    // Formatters --------------------------------------------------------------------------------

    /**
     * CSV content formatter. Convert a two-dimensional List of Objects to a CSV in an InputStream.
     * The value of each Object will be obtained by its toString() method. The fields of each CSV
     * record will be separated by the default CSV field separator, a comma.
     *
     * @param csvList A two-dimensional List of Objects representing the rows and columns of the
     *                CSV.
     * @return The InputStream containing the CSV contents (actually a ByteArrayInputStream).
     */
    public static <T extends Object> InputStream formatCsv(List<List<T>> csvList) {
        return formatCsv(csvList, DEFAULT_CSV_SEPARATOR);
    }

    /**
     * CSV content formatter. Convert a two-dimensional List of Objects to a CSV in an InputStream.
     * The value of each Object will be obtained by its toString() method. The fields of each CSV
     * record will be separated by the specified CSV field separator.
     *
     * @param csvList      A two-dimensional List of Objects representing the rows and columns of the
     *                     CSV.
     * @param csvSeparator The CSV field separator to be used.
     * @return The InputStream containing the CSV contents (actually a ByteArrayInputStream).
     */
    public static <T extends Object> InputStream formatCsv(List<List<T>> csvList, char csvSeparator) {

        // Prepare.
        StringBuilder csvContent = new StringBuilder();

        // Process records.
        for (List<T> csvRecord : csvList) {
            if (csvRecord != null) {
                csvContent.append(formatCsvRecord(csvRecord, csvSeparator));
            }

            // Add default line separator.
            csvContent.append(DEFAULT_LINE_SEPARATOR);
        }

        return new ByteArrayInputStream(csvContent.toString().getBytes());
    }

    /**
     * CSV record formatter. Convert a List of Objects representing the fields of a CSV record to a
     * String representing the CSV record. The value of each Object will be obtained by its
     * toString() method. The fields of the CSV record will be separated by the specified CSV field
     * separator.
     *
     * @param csvRecord    A List of Objects representing the fields of a CSV reecord.
     * @param csvSeparator The CSV field separator to be used.
     * @return A String representing a CSV record.
     */
    private static <T extends Object> String formatCsvRecord(List<T> csvRecord, char csvSeparator) {

        // Prepare.
        StringBuilder fields = new StringBuilder();
        String separator = String.valueOf(csvSeparator);

        // Process fields.
        for (Iterator<T> iter = csvRecord.iterator(); iter.hasNext();) {
            T object = iter.next();

            if (object != null) {
                String field = object.toString().replace("\"", "\"\""); // Escape quotes.

                if (field.contains(separator) || field.contains("\"")) {
                    field = "\"" + field + "\""; // Surround with quotes.
                }

                fields.append(field);
            }

            if (iter.hasNext()) {
                fields.append(separator); // Add field separator.
            }
        }

        return fields.toString();
    }

}
