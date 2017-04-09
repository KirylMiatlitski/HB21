package by.epam.course.basic.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.course.basic.beans.SportProduct;
import by.epam.course.basic.beans.Status;

public class Task1 {

	public static void main(String[] args) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse("SportProducts.xml");
		Document document = parser.getDocument();
		Element root = document.getDocumentElement();
		List<SportProduct> items = new ArrayList<SportProduct>();
		NodeList itemNodes = root.getElementsByTagName("SportProduct");
		SportProduct sportProduct = null;
		for (int i = 0; i < itemNodes.getLength(); i++) {
			sportProduct = new SportProduct();
			Element sportProductElement = (Element) itemNodes.item(i);
			sportProduct.setName(getSingleChild(sportProductElement, "name").getTextContent().trim());
			sportProduct.setDesctiption(getSingleChild(sportProductElement, "description").getTextContent().trim());
			sportProduct.setOwner_ID(
					Integer.parseInt(getSingleChild(sportProductElement, "Owner_ID").getTextContent().trim()));
			sportProduct
					.setStatus(Status.valueOf(getSingleChild(sportProductElement, "status").getTextContent().trim()));
			items.add(sportProduct);
		}
		for (SportProduct product : items) {
			System.out.println(product);
		}
	}

	private static Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}
}
