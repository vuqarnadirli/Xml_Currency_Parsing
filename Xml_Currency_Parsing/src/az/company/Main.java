package az.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ValCurs valCurs = new ValCurs();
    private static List<ValType> valTypeList = new ArrayList<>();
    private static List<Valute> valuteList = new ArrayList<>();


    public static void main(String[] args) {
        getDataFromXml();

        if (valTypeList != null && valuteList != null) {
            System.out.println("All currencies:");

            for (int i = 0; i < valuteList.size(); i++) {
                String code = valuteList.get(i).getCode();
                String name = valuteList.get(i).getName();
                double value = valuteList.get(i).getValue();

                System.out.println(code + " - " + " " + name + " = " + value + " AZN");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("From (code) : ");
            String fromCode = scanner.next().trim();

            System.out.print("To (code) : ");
            String toCode = scanner.next().trim();

            System.out.print("Enter amount of " + fromCode + " : ");
            double amount = scanner.nextDouble();
            double result = calculate(fromCode, toCode, amount);


            if (result != -1) {
                System.out.println(amount + " " + fromCode + " = " + result + " " + toCode);
            }


        } else {
            System.out.println("No Data!");
        }

    }

    public static Valute existValute(String code) {
        Valute valute = null;

        for (Valute val : valuteList) {
            if (val.getCode().equalsIgnoreCase(code)) {
                valute = val;
                break;
            }
        }

        return valute;
    }

    public static double calculate(String fromCode, String toCode, double amount) {
        Valute fromValute = existValute(fromCode);
        Valute toValute = existValute(toCode);

        if (fromValute == null || toValute == null) {
            System.out.println("Incorrect code!");
            return -1;
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.##");


            double result;

            if (fromValute.getNominal().equals("100")) {
                result = ((amount * fromValute.getValue()) / toValute.getValue()) / 100;
            } else {
                result = (amount * fromValute.getValue()) / toValute.getValue();
            }

            return Double.parseDouble(decimalFormat.format(result));
        }
    }

    public static void getDataFromXml() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String url = "https://www.cbar.az/currencies/" + simpleDateFormat.format(date) + ".xml";

        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = builder.parse(new URL(url).openStream());
            NodeList nodeList = document.getElementsByTagName("ValCurs");

            Element element = (Element) nodeList.item(0);

            valCurs.setDate(element.getAttribute("Date"));
            valCurs.setName(element.getAttribute("Name"));
            valCurs.setDescription(element.getAttribute("Description"));

            NodeList nodeListValType = element.getElementsByTagName("ValType");

            valuteList.add(new Valute("Azn", "1", "1 Azərbaycan manatı", 1.0));
            for (int i = 0; i < nodeListValType.getLength(); i++) {
                Element temp = (Element) nodeListValType.item(i);
                valTypeList.add(new ValType(temp.getAttribute("Type")));

                NodeList nodeListValute = temp.getElementsByTagName("Valute");

                for (int j = 0; j < nodeListValute.getLength(); j++) {
                    Element test = (Element) nodeListValute.item(j);

                    String code = test.getAttribute("Code");
                    String nominal = test.getElementsByTagName("Nominal").item(0).getTextContent();
                    String name = test.getElementsByTagName("Name").item(0).getTextContent();
                    double value = Double.parseDouble(test.getElementsByTagName("Value").item(0).getTextContent());

                    valuteList.add(new Valute(code, nominal, name, value));
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}
