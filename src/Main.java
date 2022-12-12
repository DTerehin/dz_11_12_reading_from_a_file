import java.io.*;
import java.util.Map;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) {
        //У вас имеется текстовый файл text.txt. Посчитайте в этом тексте, как часто встречается каждое слово,
        // затем выведите в отдельный файл result.txt данную информацию.
        // Также, в конец этого файла выведите самое часто встречаемое слово и его частоту.

        File fileToRead = new File("text.txt");
        File fileToWrite = new File("result.txt");

        Map<String, Integer> myTreeMap = new TreeMap<>();

        String moreOftenWord="";
        int biggestFrequency=0;

        FileReader myFileReader = null;
        FileWriter myFileWriter= null;



        if( !fileToWrite.exists()){
            try{
                if(fileToWrite.createNewFile()){
                    System.out.println("File \"result.txt\" was created. ");
                }
                else{
                    System.out.println("something WRONG! File were not created. ");
                }
            }
            catch(IOException exception){
                System.out.println(exception);
            }
        }
        else{
            System.out.printf("File %s already exists. Location %s\n", fileToWrite.getName(), fileToWrite.getAbsoluteFile());
        }

        try {
            myFileReader = new FileReader(fileToRead);
            myFileWriter = new FileWriter(fileToWrite);

            BufferedReader myBufferToRead = new BufferedReader(myFileReader);
            String line = "";


            while( (line=myBufferToRead.readLine()) != null ){
                String lineLowerCase = line.toLowerCase();
                String[] stringArray = lineLowerCase.split(" ");
                for(int i=0; i<stringArray.length; i++){
                    if(myTreeMap.containsKey(stringArray[i])){
                        myTreeMap.replace( stringArray[i], myTreeMap.get(stringArray[i])+1 );
                    }
                    else{
                        myTreeMap.put(stringArray[i],1);
                    }
                }

            }

            for(Map.Entry<String, Integer> entry : myTreeMap.entrySet()){
                if(entry.getValue()>biggestFrequency){
                    biggestFrequency = entry.getValue();
                    moreOftenWord = entry.getKey();
                }
            }
            myFileWriter.write("Самое часто встречаемое слово: "+moreOftenWord+" c частотой: "+biggestFrequency+"\n");

            System.out.println("Самое часто встречаемое слово: "+moreOftenWord+" c частотой: "+biggestFrequency);
            System.out.println("Слова с частотами записаны в файл \"result.txt\". ");


            for(Map.Entry<String, Integer> entry : myTreeMap.entrySet() ) {
                myFileWriter.write("Слово "+entry.getKey()+" : частота = "+entry.getValue()+"\n");

            }

        }
        catch(FileNotFoundException exception){
            System.out.println(exception);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally{
            try{
                myFileReader.close();
                myFileWriter.close();
            }
            catch(IOException ex) {
                System.out.println(ex);
            }
        }


    }
}