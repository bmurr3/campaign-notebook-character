package com.character.properties.language;

import com.util.List;
import com.util.Node;

/**
 * The Class LanguageList.
 * 
 * @author William Murray III
 * @version 1.0 18 July 2020
 */
public class LanguageList implements List<Language> {
	
	/** The list head. */
	private Node<Language> listHead;
	
	/** The list tail. */
	private Node<Language> listTail;
	
	/**
	 * Instantiates a new language list.
	 */
	public LanguageList() {
		this.listHead = null;
		this.listTail = null;
	}

	/**
	 * Adds the node.
	 *
	 * @param arg0 the arg 0
	 * @return true, if successful
	 */
	@Override
	public boolean addNode(Language arg0) {
		return this.addNode(new Node<Language>(arg0));
	}

	/**
	 * Adds the node.
	 *
	 * @param arg0 the arg 0
	 * @return true, if successful
	 */
	@Override
	public boolean addNode(Node<Language> arg0) {
		try {
			if (this.listHead == null && this.listTail == null) {
				this.listHead = arg0;
				this.listTail = arg0;
			} else {
				if (this.listHead == null) {
					this.listHead = this.listTail;
				} else if (this.listTail == null) {
					this.listTail = this.listHead;
				}
				
				this.listTail.setNextNode(arg0);
				arg0.setPreviousNode(this.listTail);
				this.listTail = arg0;
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	/**
	 * Clear list.
	 */
	@Override
	public void clearList() {
		this.listHead = null;
		this.listTail = null;
	}

	/**
	 * Export list as an array.
	 *
	 * @return the language[]
	 */
	@Override
	public Language[] exportList() {
		Language[] toExport = new Language[this.sizeOf()];
		Node<Language> currentNode = this.listHead;
		int indx = 0;
		
		while (currentNode != null) {
			toExport[indx] = currentNode.getData();
			indx++;
			currentNode = currentNode.getNextNode();
		}
		
		return toExport;
	}

	/**
	 * Find node.
	 *
	 * @param key the key
	 * @return the node
	 */
	@Override
	public Node<Language> findNode(Language key) {
		Node<Language> searchResult = null;
		Node<Language> currentNode = this.listHead;
		
		while (currentNode != null) {
			if (currentNode.getData().equals(key)) {
				searchResult = currentNode;
				break;
			}
			
			currentNode = currentNode.getNextNode();
		}
		
		return searchResult;
	}

	/**
	 * Import list from an array.
	 *
	 * @param dataArray the data array
	 * @return true, if successful
	 */
	@Override
	public boolean importList(Language[] dataArray) {
		try {
			this.clearList();
			for (Language currentLanguage: dataArray) {
				this.addNode(currentLanguage);
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	/**
	 * Checks if the list is empty.
	 *
	 * @return true, if is empty
	 */
	@Override
	public boolean isEmpty() {
		return (this.listHead == null) && (this.listTail == null);
	}

	/**
	 * Removes the node.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	@Override
	public boolean removeNode(Language key) {
		try {
			Node<Language> searchResult = this.findNode(key);
			
			if (searchResult == null) {
				return true;
			}
			
			if (searchResult.getPreviousNode() != null) {
				searchResult.getPreviousNode()
					.setNextNode(searchResult.getNextNode());
			}
			
			if (searchResult.getNextNode() != null) {
				searchResult.getNextNode()
					.setPreviousNode(searchResult.getPreviousNode());
			}
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	/**
	 * Removes the node.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	@Override
	public boolean removeNode(Node<Language> key) {
		return removeNode(key.getData());
	}

	/**
	 * Size of the list.
	 *
	 * @return the int
	 */
	@Override
	public int sizeOf() {
		int result = 0;
		
		try {
			Node<Language> currentNode = this.listHead;
			
			while (currentNode != null) {
				result++;
				currentNode = currentNode.getNextNode();
			}
		} catch (Exception e) {
			result = -1;
		}
		
		return result;
	}
}