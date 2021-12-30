# parser-exercise
Note: Before running the program please change the property base.dir to your working directory.

This program as 3 available endpoints:

1) POST ../parser 
   For uploading excel file.
   example: 
   request-
   curl --location --request POST 'http://localhost:8080/parser?file=valid12.xlsx' \
   --header 'Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' \
   --data-binary '@/C:/Users/Bluma_Savrasov/Documents/valid.xlsx'
   resopnse-
   C:\Users\Bluma_Savrasov\Downloads\exercise-master\exercise-master\src\main\resources\valid12.xlsx
   
2) POST ../parser/json
   For uploading excel file (by sending the content as a json body)
   example:
   request-
   curl --location --request POST 'http://localhost:8080/parser/json?file=valid2.xlsx' \
   --header 'Content-Type: application/json' \
   --data-raw '[{"name":"qwert","id":12345}]'
   response-
   C:\Users\Bluma_Savrasov\Downloads\exercise-master\exercise-master\src\main\resources\valid2.xlsx
   
3) GET ../parser/{filePath}
   For analyzing the given file.
   example:
   request-
   curl --location --request GET 'http://localhost:8080/parser/C%3A%2FUsers%2FBluma_Savrasov%2FDownloads%2Fexercise-master%2Fexercise-master%2Fsrc%2Fmain%2Fresources%2Fvalid.xlsx' \
   --header 'Content-Type: text/plain' \
   --data-binary '@'
   response-
   {
    "fileName": "valid.xlsx",
    "status": "Pass",
    "valid_rows": 1,
    "invalid_rows": 0
   }
   Note!! For the request to work it necessary to encode the filepath
   
