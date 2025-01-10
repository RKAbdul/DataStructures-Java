package org.uma.ed.datastructures.hashTable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.uma.ed.datastructures.dictionary.HashDictionary;
import org.uma.ed.datastructures.dictionary.Dictionary.Entry;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test cases for class HashDictionary")
class HashDictionaryTest {

    @Nested
    @DisplayName("A HashDictionary is created")
    class TestCasesForCreatingDictionaries {

        @Test
        @DisplayName("by calling the default constructor")
        void whenTheConstructorIsCalledThenAHashDictionaryIsCreated() {
            // Arrange
            HashDictionary<String, Integer> dictionary = new HashDictionary<>();

            // Assert
            assertNotNull(dictionary);
            assertTrue(dictionary.isEmpty());
        }

        @Test
        @DisplayName("by calling the withCapacity() method")
        void whenTheWithCapacityMethodIsCalledThenAHashDictionaryIsCreated() {
            // Arrange
            int capacity = 20;
            HashDictionary<String, Integer> dictionary = HashDictionary.withCapacity(capacity);

            // Assert
            assertNotNull(dictionary);
            assertTrue(dictionary.isEmpty());
        }

        @Test
        @DisplayName("from a sequence of entries using the of() method")
        void whenTheOfMethodIsCalledThenAHashDictionaryIsCreated() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2),
                    new Entry<>("three", 3)
            );

            // Assert
            assertNotNull(dictionary);
            assertEquals(3, dictionary.size());
        }

        @Test
        @DisplayName("from an iterable of entries using the from() method")
        void givenAnIterableOfEntriesWhenTheFromMethodIsCalledThenAHashDictionaryIsCreated() {
            // Arrange
            java.util.List<Entry<String, Integer>> entries = java.util.List.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2),
                    new Entry<>("three", 3)
            );
            HashDictionary<String, Integer> dictionary = HashDictionary.from(entries);

            // Assert
            assertNotNull(dictionary);
            assertEquals(3, dictionary.size());
        }
    }

    @Nested
    @DisplayName("The isEmpty() method")
    class TestCasesForMethodIsEmpty {

        @Test
        @DisplayName("returns true when a new dictionary is created")
        void givenANewDictionaryThenItIsEmpty() {
            // Arrange
            var dictionary = new HashDictionary<String, Integer>();

            // Assert
            assertTrue(dictionary.isEmpty());
        }

        @Test
        @DisplayName("returns false when there is an entry in the dictionary")
        void givenAnEmptyDictionaryWhenInsertAnEntryThenTheDictionaryIsNotEmpty() {
            // Arrange
            var dictionary = new HashDictionary<String, Integer>();

            // Act
            dictionary.insert(new Entry<>("key1", 1));

            // Assert
            assertFalse(dictionary.isEmpty());
        }
    }

    @Nested
    @DisplayName("The copyOf() method")
    class TestCasesForCopyOfMethod {

        @Test
        @DisplayName("works properly with an empty HashDictionary")
        void givenAnEmptyDictionaryWhenCopyOfThenTheNewDictionaryIsAlsoEmpty() {
            // Arrange
            HashDictionary<String, Integer> dictionary = new HashDictionary<>();

            // Act
            HashDictionary<String, Integer> copiedDictionary = HashDictionary.copyOf(dictionary);

            // Assert
            assertTrue(copiedDictionary.isEmpty());
            assertEquals(dictionary, copiedDictionary);
        }

        @Test
        @DisplayName("works properly with a HashDictionary with one entry")
        void givenADictionaryWithOneEntryWhenCopyOfThenTheNewDictionaryIsEqual() {
            // Arrange
            HashDictionary<String, Integer> dictionary = new HashDictionary<>();
            dictionary.insert(new Entry<>("key1", 1));

            // Act
            HashDictionary<String, Integer> copiedDictionary = HashDictionary.copyOf(dictionary);

            // Assert
            assertEquals(dictionary, copiedDictionary);
        }

        @Test
        @DisplayName("works properly with a HashDictionary with multiple entries")
        void givenADictionaryWithMultipleEntriesWhenCopyOfThenTheNewDictionaryIsEqual() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2),
                    new Entry<>("three", 3)
            );

            // Act
            HashDictionary<String, Integer> copiedDictionary = HashDictionary.copyOf(dictionary);

            // Assert
            assertEquals(dictionary, copiedDictionary);
        }
    }

    @Nested
    @DisplayName("A call to method clear()")
    class TestCasesForMethodClear {

        @Test
        @DisplayName("removes all the entries of the dictionary")
        void givenDictionaryWhenClearThenTheDictionaryIsEmpty() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2),
                    new Entry<>("three", 3)
            );

            // Act
            dictionary.clear();

            // Assert
            assertTrue(dictionary.isEmpty());
        }
    }

    @Nested
    @DisplayName("When method insert() is called")
    class TestCasesForMethodInsert {

        @Test
        @DisplayName("if the dictionary is empty, its size becomes one")
        void givenTheDictionaryIsEmptyWhenInsertThenTheDictionaryHasOneEntry() {
            // Arrange
            HashDictionary<String, Integer> dictionary = new HashDictionary<>();

            // Act
            dictionary.insert(new Entry<>("key1", 1));

            // Assert
            assertEquals(1, dictionary.size());
            assertEquals(Integer.valueOf(1), dictionary.valueOf("key1"));
        }

        @Test
        @DisplayName("if the dictionary is not empty, its size is incremented by one")
        void givenTheDictionaryIsNotEmptyWhenInsertThenTheDictionarySizeIncrementedByOne() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2)
            );

            // Act
            int currentSize = dictionary.size();
            dictionary.insert(new Entry<>("three", 3));

            // Assert
            assertEquals(currentSize + 1, dictionary.size());
            assertEquals(Integer.valueOf(3), dictionary.valueOf("three"));
        }
    }

    @Nested
    @DisplayName("When method delete() is called")
    class TestCasesForMethodDelete {

        @Test
        @DisplayName("removes the entry from the dictionary")
        void givenDictionaryWhenDeleteThenTheEntryIsRemoved() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2)
            );

            // Act
            dictionary.delete("one");

            // Assert
            assertFalse(dictionary.isDefinedAt("one"));
            assertEquals(1, dictionary.size());
        }
    }

    @Nested
    @DisplayName("When method isDefinedAt() is called")
    class TestCasesForMethodIsDefinedAt {

        @Test
        @DisplayName("returns true if the key is present in the dictionary")
        void givenDictionaryWhenKeyIsPresentThenIsDefinedAtReturnsTrue() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2)
            );

            // Assert
            assertTrue(dictionary.isDefinedAt("one"));
        }

        @Test
        @DisplayName("returns false if the key is not present in the dictionary")
        void givenDictionaryWhenKeyIsNotPresentThenIsDefinedAtReturnsFalse() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2)
            );

            // Assert
            assertFalse(dictionary.isDefinedAt("three"));
        }
    }

    @Nested
    @DisplayName("When method valueOf() is called")
    class TestCasesForMethodValueOf {

        @Test
        @DisplayName("returns the value associated with the key")
        void givenDictionaryWhenKeyIsPresentThenValueOfReturnsValue() {
            // Arrange
            HashDictionary<String, Integer> dictionary = HashDictionary.of(
                    new Entry<>("one", 1),
                    new Entry<>("two", 2)
            );

            // Assert
            assertEquals(Integer.valueOf(1), dictionary.valueOf("one"));
        }
    }
}