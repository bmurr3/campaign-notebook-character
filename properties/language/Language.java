package com.character.properties.language;

/**
 * A Language can be either written or spoken by any sentient character in the
 * game. The language has a name, and often includes a script.
 * 
 * @author William Murray III
 * @version 1.0 12 July 2020
 */
public class Language implements Comparable<Object> {
	
	/**
	 * Merge two adjacent sub arrays in a sorted order.
	 *
	 * @param languageSet The languages to be sorted
	 * @param leftIndx the left indx of the merge
	 * @param midIndx the mid indx of the merge
	 * @param rightIndx the right indx of the merge
	 */
	private static void merge(Language[] languageSet, int leftIndx, int midIndx,
			int rightIndx) {
		int leftSubArraySize = midIndx - leftIndx + 1;
		int rightSubArraySize = rightIndx - midIndx;

		Language[] leftSubArray = new Language[leftSubArraySize];
		Language[] rightSubArray = new Language[rightSubArraySize];

		for (int i = 0; i < leftSubArraySize; ++i) {
			leftSubArray[i] = languageSet[leftIndx + i];
		}
		for (int i = 0; i < rightSubArraySize; ++i) {
			rightSubArray[i] = languageSet[midIndx + 1 + i];
		}

		int i = 0, j = 0;
		int mergedIndx = leftIndx;
		while (i < leftSubArraySize && j < rightSubArraySize) {
			if (leftSubArray[i].compareTo(rightSubArray[j]) <= 0) {
				languageSet[mergedIndx] = leftSubArray[i];
				i++;
			} else {
				languageSet[mergedIndx] = rightSubArray[j];
				j++;
			}
			mergedIndx++;
		}

		while (i < leftSubArraySize) {
			languageSet[mergedIndx++] = leftSubArray[i++];
		}
		while (j < rightSubArraySize) {
			languageSet[mergedIndx++] = rightSubArray[j++];
		}
	}
	
	/**
	 * Sort the Language array. Uses the Merge Sort algorithm
	 *
	 * @param languageSet The Languages to be sorted
	 * @param leftIndx the left indx of this array or sub array
	 * @param rightIndx the right indx of this array or sub array
	 */
	private static void sort(Language[] languageSet, int leftIndx,
			int rightIndx) {
		if (leftIndx < rightIndx) {
			int midIndx = (leftIndx + rightIndx) / 2;

			sort(languageSet, leftIndx, midIndx);
			sort(languageSet, midIndx + 1, rightIndx);

			merge(languageSet, leftIndx, midIndx, rightIndx);
		}
	}

	/**
	 * Sort languages from an array.
	 *
	 * @param languageSet The languages to be sorted.
	 */
	public static void sortLanguages(Language[] languageSet) {
		Language.sort(languageSet, 0, languageSet.length - 1);
	}

	/** The language name. */
	private String languageName;

	/** The written script. */
	private String writtenScript;

	/**
	 * Instantiates a new language. Uses the default language of "Common" with
	 * the "Common" script.
	 */
	public Language() {
		this.languageName = "Common";
		this.writtenScript = "Common";
	}

	/**
	 * Instantiates a new language.
	 *
	 * @param languageName the language name
	 */
	public Language(String languageName) {
		this.languageName = languageName;
		this.writtenScript = null;
	}

	/**
	 * Instantiates a new language.
	 *
	 * @param languageName the language name
	 * @param writtenScript the written script
	 */
	public Language(String languageName, String writtenScript) {
		this.languageName = languageName;
		this.writtenScript = writtenScript;
	}

	/**
	 * Compare to.
	 *
	 * @param o the object to compare
	 * @return is 0 if the object exactly matches this Language. Is negative if
	 * this object precedes the passed object. Is positive if this object comes
	 * after the passed object. 
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	@Override
	public int compareTo(Object o) throws IllegalArgumentException {
		if (o.getClass() != this.getClass()) {
			throw new IllegalArgumentException("Illegal Argument. The " +
					"argument given was of type: " + o.getClass()
					+ ". Expected class: " + this.getClass());
		}
		Language toCompare = (Language) o;

		if (this.languageName.compareToIgnoreCase(toCompare.languageName)
				!= 0) {
			return this.languageName.compareToIgnoreCase(
					toCompare.languageName);
		} else {
			if (this.writtenScript == null) {
				if (toCompare.writtenScript == null) {
					return 0;
				} else {
					return -1;
				}
			} else {
				if (toCompare.writtenScript == null) {
					return 1;
				} else {
					return this.writtenScript.compareToIgnoreCase(
							toCompare.writtenScript);
				}
			}
		}
	}

	/**
	 * Gets the language name.
	 *
	 * @return the language name
	 */
	public String getLanguageName() {
		return languageName;
	}

	/**
	 * Gets the written script.
	 *
	 * @return the written script
	 */
	public String getWrittenScript() {
		return writtenScript;
	}

	/**
	 * Sets the language name.
	 *
	 * @param languageName the new language name
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * Sets the written script.
	 *
	 * @param writtenScript the new written script
	 */
	public void setWrittenScript(String writtenScript) {
		this.writtenScript = writtenScript;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Language [languageName=" + languageName + ", writtenScript=" +
				writtenScript + "]";
	}
}