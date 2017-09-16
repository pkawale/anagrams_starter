


/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

        package com.google.engedu.anagrams;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.Reader;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> wordList= new ArrayList<String>();
    HashSet<String> wordSet= new HashSet<String>();
    HashMap<Integer,ArrayList<String>> sizeToWord= new HashMap<>();
    HashMap<String,ArrayList<String>>letterstoWord = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);

            ArrayList<String> mylist = new ArrayList<String>();
            if(!letterstoWord.containsKey(word))
            {
                mylist.add(word);
                letterstoWord.put(sort(word),mylist);

            }
            else
            {
                mylist= (ArrayList)letterstoWord.get(sort(word));
                mylist.add(word);
                letterstoWord.put(sort(word),mylist);

            }
            sizeToWord.put(word.length(),wordList);


        }
    }

    public String putinWords(String word)
    {
        int i=0;
        int size=word.length();
        ArrayList<String> temp=new ArrayList<String>();
        word=temp.get(i);
        i++;//global
        if(i>temp.size())
        {
            i=0;
            size++;
        }
        //check size of word

        return word;
    }
    public boolean isGoodWord(String word, String base)
    {
        if(wordSet.contains(word) && !word.contains(base))
            return true;


        return false;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        for(int i=0;i< wordList.size();i++)
        {

            String fromList= wordList.get(i);

            if(isAnagram(targetWord,fromList))
            {
                result.add(fromList);
            }

        }

        return result;
    }

    public boolean isAnagram(String firstWord,String secondword)
    {

        String wordOne= sort(firstWord);
        String wordTwo = sort(secondword);

        if(wordOne.equalsIgnoreCase(wordTwo))
            return true;
        else
            return false;
    }



    public String sort(String myWord)
    {

        char[] myarray= myWord.toCharArray();
        Arrays.sort(myarray);

        return (new String(myarray));

    }


    public List<String> getAnagramsWithOneMoreLetter(String word) {

        ArrayList<String> temp;
        ArrayList<String> result = new ArrayList<String>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            String newWord = word + alphabet;
            String sortedKey = sort(newWord);
            if (letterstoWord.containsKey(sortedKey)) {
                temp = new ArrayList();
                temp = (ArrayList) letterstoWord.get(sortedKey);
                for (int i = 0; i < temp.size(); i++)
                    result.add(String.valueOf(temp.get(i)));
            }

        }
        return result;
    }
    public String pickGoodStarterWord() {

        Random i = new Random();
        int index = i.nextInt();
        while (index<wordList.size()) {

            String randomWord = wordList.get(index);
            String sw = sort(randomWord);
            ArrayList<String> temp = new ArrayList<String>();

            if (letterstoWord.containsKey(sw)) {
                temp = letterstoWord.get(sw);
                if (!(temp.size() < MIN_NUM_ANAGRAMS)) {
                    return randomWord;
                }
                else
                {
                    index=i.nextInt();
                }
            }

        }
        return "Stop";
    }
}
