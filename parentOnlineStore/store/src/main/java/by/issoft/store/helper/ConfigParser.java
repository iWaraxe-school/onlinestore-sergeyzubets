package by.issoft.store.helper;

import by.issoft.store.utilities.StoreConstants;
import com.google.common.base.Preconditions;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigParser {

    private static final String configFileIsNotFoundErrorMessage = StoreConstants.StoreConfigFile.CONFIG_FILE_IS_NOT_FOUND_ERROR_MESSAGE;
    private static final String configFileName = "config.xml";

    public Map<String, String> getSortingConfigFromFile() {
        Map<String, String> sortRules = new LinkedHashMap<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            URL configFileUrl = ConfigParser.class.getClassLoader().getResource(configFileName);
            Preconditions.checkArgument(configFileUrl != null, configFileIsNotFoundErrorMessage);

            Document configDoc = dBuilder.parse(configFileUrl.getPath());
            NodeList nodeList = configDoc.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    sortRules.put(node.getNodeName().toLowerCase(), node.getTextContent().toUpperCase());
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return sortRules;


    }

}
