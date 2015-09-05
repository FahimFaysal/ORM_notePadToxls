    import java.io.BufferedReader;
    import java.io.FileOutputStream;
    import java.io.FileReader;
    import java.io.IOException;
    import org.apache.poi.hssf.usermodel.HSSFRow;
    import org.apache.poi.hssf.usermodel.HSSFSheet;
    import org.apache.poi.hssf.usermodel.HSSFWorkbook;
    
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fahim Foysal
 */

    class test{
        public int calculation(String correctFilePath, String studentFilePath, String path, int nv) throws IOException{
            // ////////////////// read correct answer////////////////////////
            FileReader frsc = new FileReader(correctFilePath);
            BufferedReader brc = new BufferedReader(frsc);

            String getCorrectData[] = new String[6], // b ABCDE THE SET NO
                st;
            getCorrectData[0] = getCorrectData[1]= getCorrectData[2]= getCorrectData[3]= getCorrectData[4]=getCorrectData[5] = "";

            while ((st = brc.readLine()) != null) {

                 st = getEnd(st);  // count the questino number

                int setVal ;
                setVal = getSet(st);
                // System.out.println(setVal);
                getCorrectData[setVal] = st.substring(0, st.length());
                //System.out.println(getCorrectData[setVal]);

                //System.out.println(setVal+"\t"+ getCorrectData[setVal]);

            }
            frsc.close();
            // System.out.println("correct file is closed");
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // //////////////////read student result////////////////////////
            FileReader frs = new FileReader(studentFilePath);
            BufferedReader brs = new BufferedReader(frs);

    // /////////////////////// file writing /////////////////////////
            //FileWriter fr = new FileWriter(resultFilePath, true); // file open
            //FileOutputStream fos = new FileOutputStream(resultFilePath);
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            //String sr = "sequence   stdNo   level   term    number  testNo  date    setNo   correctNumber   wrongNumber    result";
            //String s = "\r\n";
             String filename = path+".xls";

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("sequence");
            rowhead.createCell((short) 1).setCellValue("stdNo");
            rowhead.createCell((short) 2).setCellValue("level");
            rowhead.createCell((short) 3).setCellValue("term");
            rowhead.createCell((short) 4).setCellValue("number");
            rowhead.createCell((short) 5).setCellValue("testNo");
            rowhead.createCell((short) 6).setCellValue("date");
            rowhead.createCell((short) 7).setCellValue("setNo");
            rowhead.createCell((short) 8).setCellValue("correctNumber");
            rowhead.createCell((short) 9).setCellValue("Number of wrong ans");
             rowhead.createCell((short) 10).setCellValue("Reduced Mark");
            rowhead.createCell((short) 11).setCellValue("result");


            String sr;

            int i = 1;
            while ((st = brs.readLine()) != null) {
    /////////////////////////////////////////////////////////////////////
            int sequence, stdNo, prf1, prf2, setVal , c = 0, w = 0;
            String date, number, testNo, wa = "",
            level, term, setNo;
            double  result = 0.0;

            sequence = Integer.parseInt(st.substring(1, 11));
            stdNo = Integer.parseInt(st.substring(12, 19));

            //level = st.charAt(19);
            level = st.substring(19, 20);
            //term = st.charAt(20);
            term = st.substring(20, 21);

          //pref1 and pref 4 24

         // number = Integer.parseInt(st.substring(25, 29)); 
            number = (st.substring(25, 29));

          //testNo = Integer.parseInt(st.substring(29, 30));
            testNo = st.substring(29, 30);

            date = st.substring(30, 32)+"/"+st.substring(32, 34)+"/"+st.substring(34, 36);

            //setNo = st.charAt(36);
            setNo = st.substring(36, 37);

           // System.out.println(sequence+"==="+stdNo+"=="+level+"=="+term+"=="+ number+"=="+testNo+"=="+date+"=="+setNo);

            setVal = getSet(st);
          //System.out.println("The set val : "+setVal);
           double wrongAnswer = 0.0;
            if(getCorrectData[setVal] == "")
                wa= "Set not matched";
                else{
            int data[] = Calculate(st, getCorrectData[setVal]); 
              //data =  Calculate(st, getCorrectData[setVal], nv); 
             c = data[0];
                    w = data[1];
            double negative = nv / 100.0,
                    right = c*1.0,

                    wrongAns = w*negative;

                     wrongAnswer = wrongAns;

                    result = right - wrongAns;

             wa = "";
            wa = wa+wrongAns+'('+w+')';

          }                                               
     ///////////////////////////////////////////////////////////////////////
          sr = sequence+"\t"+stdNo+"\t"+level+"\t"+term+"\t"+ number+"\t"+testNo+"\t"+date+"\t"+setNo+"\t"+c+"\t"+wa+"\t"+result;
    //System.out.println(sr);
          /////////////////////////write to excel///////////////////////////////////
    //String  path = "C:\\Users\\Fahim Foysal\\Documents\\",
                      // file = "Papri";


                            HSSFRow row = sheet.createRow((short) i);
                            row.createCell((short) 0).setCellValue(sequence);
                            row.createCell((short) 1).setCellValue(stdNo);
                            row.createCell((short) 2).setCellValue(level);
                            row.createCell((short) 3).setCellValue(term);
                            row.createCell((short) 4).setCellValue(number);
                            row.createCell((short) 5).setCellValue(testNo);
                            row.createCell((short) 6).setCellValue(date);
                            row.createCell((short) 7).setCellValue(setNo);
                            row.createCell((short) 8).setCellValue(c);
                            row.createCell((short) 9).setCellValue(w);
                            row.createCell((short) 10).setCellValue(wrongAnswer);
                            row.createCell((short) 11).setCellValue(result);


                             FileOutputStream fileOut = new FileOutputStream(filename);
                            workbook.write(fileOut);
                            fileOut.close();

                            //System.out.println("Your excel file has been generated!");                                                

                                     i++;               
          ////////////////////////////////////////////////////////////                                          

                    }

                    frs.close();
                   // System.out.println("file is closed");

            return 5;

      }
        private static String getEnd(String st) {
        // TODO Auto-generated method stub
        // Stirng s = "";
        int last = 37, i;
        for (i = 37; i < st.length() - 2; i++) {
          if(st.charAt(i) == 65||st.charAt(i) == 66||st.charAt(i) == 67||st.charAt(i) == 68||st.charAt(i) == 69){
            last = i;
            //System.out.println(st.charAt(i)+"\t"+i+"\t"+last);
          }
        }
        st = st.substring(0, last+1);
        //System.out.println(st);
        return st;
      }

         private static int getSet(String st){
        int setVal = 0;
        if(st.charAt(36) == 'A')
          setVal = 1;
        else if(st.charAt(36) == 'B')
          setVal = 2;
        else if (st.charAt(36) == 'C')
          setVal = 3; 
        else if (st.charAt(36) == 'D')
          setVal = 4; 
        else if (st.charAt(36) == 'E')
          setVal = 5; 
        else if (st.charAt(36) == 'd')
          setVal = 0; 

        return  setVal;
      }
        private static int[] Calculate(String std, String rt){
           int c = 0, w = 0, ca;
        //System.out.println(rt);
        //System.out.println(std);                       
        for(int j = 37; j<=rt.length()-1; j++){
                if(rt.charAt(j) == std.charAt(j))
              c ++;
            else if (std.charAt(j) != 'b')
                w++;
          //  System.out.println(j);
              }
           return new int[]{c,w};
      }

    }
