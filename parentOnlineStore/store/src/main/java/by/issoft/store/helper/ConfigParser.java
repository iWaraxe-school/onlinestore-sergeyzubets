package by.issoft.store.helper;

import by.issoft.store.Store;
import by.issoft.store.utilities.SortOption;
import com.google.common.base.Preconditions;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static by.issoft.store.utilities.StoreConstants.StoreConfigFile.*;

public class ConfigParser {

    public ConfigParser() {
    }

    public Map<String, String> getSortingConfigFromFile() {
        Map<String, String> sortRules = new LinkedHashMap<>();
        String sortEnumOptions = Arrays.asList(SortOption.values()).toString();
        Locale storeLocale = Store.getInstance().getStoreLocale();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            URL configFileUrl = ConfigParser.class.getClassLoader().getResource(DEFAULT_CONFIG_FILE_NAME);
            Preconditions.checkArgument(configFileUrl != null, CONFIG_FILE_IS_NOT_FOUND_ERROR_MESSAGE);

            Document configDoc = dBuilder.parse(configFileUrl.getPath());
            NodeList nodeList = configDoc.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Preconditions.checkArgument(sortEnumOptions.contains(node.getTextContent().toUpperCase(storeLocale)),
                            INCORRECT_SORT_OPTION_ERROR_MESSAGE + node.getTextContent().toLowerCase(storeLocale));
                    sortRules.put(node.getNodeName().toLowerCase(), node.getTextContent().toUpperCase());
                }
            }
            Preconditions.checkArgument(sortRules.size() > 0, CONFIG_FILE_WITHOUT_CONFIG);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        return sortRules;
    }
}
