package com.example.vgproject2.controller;

import com.example.vgproject2.entity.Demo;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Column;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

public class UploadRegisterStudentAction {

//    @Autowired
//    private Zfcuser_Auth_Service auth;
//
//    @Autowired
//    private Config config;


//    @Autowired
//    private TstudentregistrationexcelDetailsTable excelUploadStuObj;
//
//    @Autowired
//    private UserTable tableuser;
//
//    @Autowired
//    private SubjectTeacherMappingTable subjectTeacherMappingTable;

//        private userObj = zfcUserAuthenication.getIdentity();
//        private userId = zfcUserAuthenication.getIdentity().getId();
//
//
//    String success="";
//    String error="";
//
//    public void authenticate()
//    {
//        if(auth.hasIdentity())
//        {
//            return true;
//        }
//        return false;
//    }


    public static String md5_file(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (Exception e)
        {
           return null;
        }
    }


    public boolean excelFormatValidationStudent(int i,String val,String upload_action,String stakholder){
        if(upload_action.equals(""))
            upload_action="";
        if(stakholder.equals(""))
            stakholder="";
        switch (i) {
            case 1 :
                if (val != "Name") {
                    return false;
                }
                return true;
            case 2 :
                if (val != "Email") {
                    return false;
                }
                return true;
            case 3 :
                if (val != "Mobile Number") {
                    return false;
                }
                return true;
            case 4 :
                if(stakholder!="student"){
                    if (val != "State") {
                        return false;
                    }
                }else if (val != "Board") {
                    return false;
                }
                return true;
            case 5 :
                if(stakholder!="student"){
                    if (val != "City") {
                        return false;
                    }
                }else if (val != "Class") {
                    return false;
                }
                return true;
            case 6 :
                if(stakholder!="student" && upload_action=="reg"){
                    if (val != "User Country") {
                        return false;
                    }
                } else if(stakholder!="student"){
                    if (val != "Pack ID (s)") {
                        return false;
                    }
                }else if (val != "School Code") {
                    return false;
                }
                return true;
            case 7 :
//                if(stakholder=="teacher"){
//                    if (strtolower(val) != "school code") {
//                     return false;
//                    }
//                }
//                if(stakholder!="student" && stakholder!="teacher"){
                if(stakholder!="student"){
                    if (val != "MIS Tab name") {
                        return false;
                    }
                }
                if(stakholder=="student"){
                    if (val != "School Name") {
                        return false;
                    }
                }
                return true;
            case 8 :
                if(stakholder=="student"){
                    if (val != "State") {
                        return false;
                    }
                }
                if(stakholder!="student" && upload_action=="reg_pack"){
                    if (val != "Discount %") {
                        return false;
                    }
                }
                return true;
            case 9 :
                if(stakholder=="student"){
                    if (val != "City") {
                        return false;
                    }
                }
                if(stakholder !="student" && upload_action=="reg_pack"){
                    if (val != "Discount Amount") {
                        return false;
                    }
                }
                return true;
            case 10 :
                if(stakholder=="student" && upload_action=="reg_camp"){
                    if (val != "Free subscription campaign") {
                        return false;
                    }
                }else if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "Pack ID (s)") {
                        return false;
                    }
                } else if(stakholder !="student" && upload_action=="reg_pack"){
                    if (val != "Coupon Code") {
                        return false;
                    }
                } else if(stakholder=="student" && upload_action=="reg"){
                    if (val != "Section") {
                        return false;
                    }
                }
                return true;
            case 11 :
                if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "MIS Tab name") {
                        return false;
                    }
                }else if(stakholder=="student" && upload_action=="kalam_reg_pack"){
                    if (val != "Voucher Code") {
                        return false;
                    }
                } else if(stakholder !="student" && upload_action=="reg_pack"){
                    if (val != "Payment mode") {
                        return false;
                    }
                } else if(stakholder=="student" && upload_action=="reg_camp"){
                    if (val != "Section") {
                        return false;
                    }
                }else if(stakholder=="student" && upload_action=="reg"){
                    if (val != "User Country") {
                        return false;
                    }
                }
                return true;
            case 12 :
                if(stakholder!="student"){
                    if (val != "Remark") {
                        return false;
                    }
                }
                if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "Discount %") {
                        return false;
                    }
                }
                return true;
            case 13 :
                if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "Discount Amount") {
                        return false;
                    }
                }else if(stakholder!="student" && upload_action=="reg_pack"){
                    if (val != "Purchase Date") {
                        return false;
                    }
                }else if(stakholder=="student" && upload_action=="kalam_reg_pack"){
                    if (val != "Section") {
                        return false;
                    }
                }
                return true;
            case 14 :
                if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "Coupon Code") {
                        return false;
                    }
                }
                return true;
            case 15 :
                if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "Payment mode") {
                        return false;
                    }
                }
                return true;
            case 16 :
                if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "Remark") {
                        return false;
                    }
                }
                if(stakholder!="student" && upload_action=="reg_pack"){
                    if (val != "Package Expiry Date") {
                        return false;
                    }
                }
                return true;
            case 17 :
                if(stakholder=="student" && upload_action=="reg_pack"){
                    if (val != "Purchase Date") {
                        return false;
                    }
                }
                if (stakholder != "student" && upload_action == "reg_pack") {
                    if (val != "Paid Price") {
                        return false;
                    }
                }
                return true;
            case 18 :
                if (stakholder == "student" && upload_action == "reg_pack") {
                    if (val != "Invoice Type") {
                        return false;
                    }
                }
                return true;
            case 19 :
                if(stakholder=="student" && upload_action=="reg_pack") {
                    if (val != "Section") {
                        return false;
                    }
                }
                return true;
            case 20 :
                if(stakholder=="student" && upload_action=="reg_pack") {
                    if (val != "Gift Code") {
                        return false;
                    }
                }
                return true;
            case 21 :
                if(stakholder=="student" && upload_action=="reg_pack") {
                    if (val != "User Country") {
                        return false;
                    }
                }
                return true;
            case 22 :
                if(stakholder=="student" && upload_action=="reg_pack") {
                    if (val != "Package Expiry Date") {
                        return false;
                    }
                }
                return true;
            case 23 :
                if (stakholder == "student" && upload_action == "reg_pack") {
                    if (val != "Paid Price") {
                        return false;
                    }
                }
                return true;
            case 24 :
                if (stakholder == "student" && upload_action == "reg_pack") {
                    if (val != "Batch Id") {
                        return false;
                    }
                }
                return true;
            case 25 :
                if (stakholder == "student" && upload_action == "reg_pack") {
                    if (val != "Reference Number") {
                        return false;
                    }
                }
                return true;
        }
        return  false;
    }

        private int userId=1;

    @PostMapping("/uploadRegisterStudentAction")
    public void uploadRegisterStudentAction(@RequestBody Demo demo)
    {
        Demo postData= demo;
        int empcode = userId;
        String stakeholder = postData.getStakeholder();
        String registervia = "mailid";
        String upload_action = "school_reg";
        int uploaded_for = userId;

        String auto_generated_mail = "no";
        String auto_generated_sms = "no";
        int send_otp = 0;
        String save_old_data = postData.getSave_old_data();
        String gts_platform_type = "other";
        // School Logo
        String school_logo = "";
//        String  tickerfile[] = postData.getTickerfile().split("\\");
//
//        String uploadedFileName = tickerfile[tickerfile.length-1];
//
//        String checksum = md5_file(_FILES['tickerfile']['tmp_name']);
//
//        String fileNameArr = uploadedFileName.split(".");
//
//        String path = _FILES['tickerfile']['tmp_name'];

        String path=demo.getPath();


        FileInputStream file=null;
        Workbook workbook=null;
        try {
            file = new FileInputStream(new File(path));
            workbook = new XSSFWorkbook(file);
        }catch (Exception e)
        {

        }

        Sheet sheet = workbook.getSheetAt(0);
        // Excel format validation

        List<String> excelColumnsArray = new ArrayList<>();
        boolean excelFormatValid = true;
        int excelcolumns = 12;
        if (stakeholder=="school_student" &&  upload_action == "school_reg"){

            excelColumnsArray = Arrays.asList("name", "email", "mobile", "admission_no", "board", "class", "section", "school_name", "school_code", "state", "city", "user_country");
            registervia="admission_no";
        }

        Sheet worksheet = workbook.getSheetAt(0);

        int highestColumnIndex = worksheet.getRow(0).getLastCellNum();
        char highestColumn = (char)('A'+highestColumnIndex); // e.g 'F'
        char lastHighestColumn = highestColumn;

        if(excelcolumns!=(highestColumnIndex-1)){
            String errors = "Invalid Excel Format";
//            Map<String,String> result=new HashMap<>();
//            result.put()
//                    "error" : errors,
//                    "success" : success,
//                    "postData" : postData
//                      );
//            echo json_encode(result);die;
        }


        if (!Arrays.asList("school_student","school_teacher").contains(stakeholder)  &&  !Arrays.asList("school_reg").contains(upload_action)){
            for (int i = 1; i <= excelcolumns; i++) {
                Row row = sheet.getRow(0);
                Cell cell = row.getCell(i - 1);
                String val = cell.toString();
                excelFormatValid = excelFormatValidationStudent(i, val, upload_action,stakeholder);
                if (!excelFormatValid) {
                    String errors = "Excel format is wrong";
//                    result=array(
//                            'error' => errors,
//                            'success' => success,
//                            'postData' => postData
//                      );
//                    echo json_encode(result);die;
                }
            }
        }

        // Excel format validation ends here
        objWriter = \PHPExcel_IOFactory::createWriter(objPHPExcel, 'Excel5');
        excelUploadStuObj = this->getServiceLocator()->get('Assessment\Model\TstudentsregistrationexceldetailsTable');
        if (count(objPHPExcel->getAllSheets()) != '1') {
            errors[] = "Excel file should contain only 1 worksheet";
            result=array(
                    'error' => errors,
                    'success' => success,
                    'postData' => postData
                      );
            echo json_encode(result);die;
        }

        exstingExcelUpload = excelUploadStuObj->getStudentsUploadDataByChecksum(checksum);
        if (in_array(stakeholder,array('school_student','school_teacher'))){
            exstingExcelUpload=array();
        }
        lmsServiceObj = this->getServiceLocator()->get('lms_container_service');
        comMapperObj = this->getServiceLocator()->get("com_mapper");
        tableuser = this->getServiceLocator()->get('Assessment\Model\UserTable');
        websiteMapper = this->getServiceLocator()->get('website_mapper');
        emIdolUserTable = this->getServiceLocator()->get('EmIdol\Model\EmIdolUserTable');
        emIdolUserDataTable = this->getServiceLocator()->get('EmIdol\Model\EmIdolUserDataTable');
        otherTable = this->getServiceLocator()->get('Assessment\Model\UserOtherDetailsTable');
        sectionMasterTblObj = this->getServiceLocator()->get('Api\Model\SectionMasterTable');
        salesschooldetailsTable = this->getServiceLocator()->get('Admin\Model\SalesSchoolDetailsTable');
        cityCondition = "sales_school_details.school_id ='". _SESSION['login_detail']->school_id."'";
        school_data = salesschooldetailsTable->getOnlySchoolData(cityCondition)->current();

        gtsSchoolTable = this->getServiceLocator()->get('Admin\Model\GtsSchoolDetailsTable');
        orderby ='user.user_type_id DESC';



        // create a condition if user.user_type_id=1 &&  user.school_id=_SESSION['login_detail']->school_id and user.user_id in
        //(select user_id from user_other_details where key_name ='register_source' and `value`='school_excel_upload') GROUP BY user.user_id")
        conditions = " user.user_type_id = 1 and user.school_id="._SESSION['login_detail']->school_id."  and user.user_id  in (select user_id from user_other_details where key_name ='register_source' and `value`='school_excel_upload') GROUP BY user.user_id";


        //getting list of student by above condition and order by user.user_type_id DESC
        studentList = tableuser->getStudentRecords(conditions,orderby);

        //create condition gts_school_details.school_id='_SESSION['login_detail']->school_id'
        conditions =" gts_school_details.school_id="._SESSION['login_detail']->school_id. " ";

        //getting array of records with columns 
        //'id','school_id','impl_form_no','board_id','ca_validity_teacher_check','ca_validity_student_check','ca_validity_coordinator_check',
        //'teacher_limit','student_limit','coordinator_limit','school_status','ca_validity_teacher_from_date',
        //'ca_validity_teacher_to_date','ca_validity_student_from_date',
        //'ca_validity_student_to_date','ca_validity_coordinator_from_date','ca_validity_coordinator_to_date','expiry_date','ms_status'
        // where (condition) of Arra
        gtstableCheck =gtsSchoolTable->getSchoolDataBySchoolId(conditions);

        //student_limit-count(studentList) 
        less =gtstableCheck[0]['student_limit'] - count(studentList);



        // why exstingExcelUpload = array()
        if(count(exstingExcelUpload) <= 0) {

            //pointer to first sheet 
            worksheet = objPHPExcel->setActiveSheetIndex(0);
            //get title
            worksheetTitle = worksheet->getTitle();
            highestRow = worksheet->getHighestRow(); // e.g. 10
            if(stakeholder=='school_student')
            {
                file_upload_type ='reg_student';
            }
            else
            {
                file_upload_type ='reg_teacher';
            }
            //total rows
            totrow =highestRow -1;

            errors=array();//<pre>35+-1+21+14+39sdfd
            //35+38+21+14+39sdfd
            //                echo "<pre>";print_r(gtstableCheck[0]['student_limit']);print_r("+".totrow); print_r("+".count(studentList));
//                print_r("+".less); print_r("+".highestRow);print_r(count(studentList) > gtstableCheck[0]['student_limit']);die("sdfd");

            //why not used totrow 
            //totrows must be less that 250
            if(highestRow -1 > 250){
                duplicate = 1;
                errors[] = " : Maximum 250 records can be uploaded at one go. Please check and reupload.";
                uplaodError .= "Maximum 250 records can be uploaded at one go. Please check and reupload,";
            }

            // count(studentList) must be less than equal to gtstableCheck[0]['student_limit'] && totrows must be less than (gtstableCheck[0]['student_limit']- count(studentList))
            if(count(studentList) > gtstableCheck[0]['student_limit'] || totrow > less){
                duplicate = 1;
                errors[] = " User limit exceeded, Remaining count: ".less.". To add more, please contact to 'Extramarks Admin.'";
                uplaodError .= "User limit exceeded, Remaining count: ".less.". To add more, please contact to 'Extramarks Admin.'";
                result=array(
                        'error' => errors,
                        'success' => success,
                        'postData' => postData
                           );
                echo json_encode(result);die;

            }

            // highestRow=worksheet->getHighestRow()  
            for (row = 2; row <= highestRow; ++row) {
                rowData = array();

                // highestColumnIndex = \PHPExcel_Cell::columnIndexFromString(highestColumn);      
                for (col = 1; col < highestColumnIndex; ++col) {

                    //get cell
                    cell = worksheet->getCellByColumnAndRow(col, row);

                    //get value of cell and trim
                    val = trim(cell->getValue());

                    //if col is 22 and val is not empty
                    if(col==22 && !empty(val)){

                        // date('Y-m-d', \PHPExcel_Shared_Date::ExcelToPHP(val))
                        // date(format, timestamp)

                        //\PHPExcel_Shared_Date::ExcelToPHP(val)
                        //static method ExcelToPHP
                        //static long ExcelToPHP( [long dateValue = 0])
                        //Convert a date from Excel to PHP

                        //Convert a date from Excel to PHP
                        //ExcelToPHP(\long dateValue, boolean adjustToTimezone, string timezone) : \long

                        newVal = date('Y-m-d', \PHPExcel_Shared_Date::ExcelToPHP(val));

                        //strtotime(time, now) 
                        if(strtotime(newVal)>strtotime("now")){
                            val=newVal;
                        }
                    }
                    rowData[] = val;

                } //end of loop for (col = 1; col < highestColumnIndex; ++col)
                //print_r(rowData);die;


                // check rowData[8]!=school_data->school_code
                if(rowData[8]!=school_data->school_code){
                    duplicate = 1;
                    errors[] = (row - 1)." : School Code is incorrect. Please contact to 'Extramarks Admin.'";
                    uplaodError .= "School Code is incorrect. Please contact to 'Extramarks Admin.',";
                }

                //check rowData[0]==empty
                if (empty(rowData[0])) {
                    duplicate = 1;
                    errors[] = (row - 1)." : Name should not be blank in uploaded excel. Please check and reupload.";
                    uplaodError .= "Name should not be blank in uploaded excel. Please check and reupload.,";

                    //  Name should be between 3 to 100 characters 
                } else if (strlen(rowData[0]) > 100 || strlen(rowData[0]) < 3) {
                    duplicate = 1;
                    errors[] = (row - 1)." : Name should be between 3 to 100 characters";
                    uplaodError .= " Name should be between 3 to 100 characters,";

                }

                // Invalid Name. Please check and reupload and valid regex check 
                else {
                    if (rowData[0] != "" && preg_match('/[\'^0-9£%&*()}{@#~?><>,|=_+¬-]/', rowData[0])) {
                        duplicate = 1;
                        errors[] = (row - 1)." : Invalid Name. Please check and reupload";
                        uplaodError .= " Invalid Name. Please check and reupload,";

                    }
                }

                //regex 
                regex = '/^[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*(\.[a-zA-Z]{2,3})/';

                //Invalid Email ID. Please check and reupload
                if(!empty(rowData[1])){
                    if (!preg_match(regex, rowData[1])) {
                        duplicate = 1;
                        errors[] = (row - 1) . " : " . rowData[1] . "  Invalid Email ID. Please check and reupload.";
                        uplaodError .= "Invalid Email ID. Please check and reupload., ";
                    }
                }


                //  Any one information is required amongst email id and mobile number.
                if (empty(rowData[2]) && empty(rowData[1])) {
                    duplicate = 1;
                    errors[] = (row - 1) . " : Any one information is required amongst email id and mobile number.";
                    uplaodError .= "Any one information is required amongst email id and mobile number., ";
                }

                //regex
                regexx = "/^(\d[\s-]?)?[\(\[\s-]{0,2}?\d{3}[\)\]\s-]{0,2}?\d{3}[\s-]?\d{4}/i";


                if(empty(rowData[2])) {

                }
                else{

                    // Invalid mobile number. Please check and reupload.
                    if (!preg_match(regexx, rowData[2])) {
                        duplicate = 1;
                        errors[] = (row - 1) . " : " . rowData[2] . "  Invalid mobile number. Please check and reupload.";
                        uplaodError .= "Invalid mobile number. Please check and reupload, ";
                    }// Mobile number shall be 10 digits
                    elseif (strlen(rowData[2]) != '10') {
                        duplicate = 1;
                        errors[] = (row - 1) . " : " . rowData[2] . "  Mobile number shall be 10 digits";
                        uplaodError .= "Mobile number shall be 10 digits, ";
                    }//Please enter valid mobile number
                    elseif(!preg_match('/(6|7|8|9)\d{9}/', rowData[2])){
                        duplicate = 1;
                        errors[] = (row - 1) . " : " . rowData[2] . "  Please enter valid mobile number";
                        uplaodError .= "Please enter valid mobile number, ";
                    }
                }
                    /*if(!empty(rowData[2])){
                        alreadyLoginIdentityUserId='';
                        userDetails =tableuser->getuserbyusername(rowData[2]);
                        if (empty(userDetails)) {
                           
                        } else {
                            if (userDetails->user_id != '') {
                                duplicate = 1;
                                errors[] = (row - 1) . " : " . rowData[2] . " User Mobile already exists";
                                uplaodError .= " User Mobile already exists";
                            }
                            
                        }
                    }*/


                // Admission Number cannot be blank. Please check and reupload.
                if (in_array(stakeholder,   array('school_student'))) {
                    if (empty(rowData[3]) || rowData[3]=='') {
                        duplicate = 1;
                        errors[] = (row - 1) . " : Admission Number cannot be blank. Please check and reupload.";
                        uplaodError .= "Admission Number cannot be blank. Please check and reupload, ";
                    }
                }

                //validate section id
                if(!empty(rowData[6])){

                    //Autowiring SectionMasterTable
                    sectionMasterTblObj = this->getServiceLocator()->get('Api\Model\SectionMasterTable');

                    //getting sections details using section name
                    sectionDataArr=sectionMasterTblObj->getSectionDataWhere(array('section_name'=>rowData[6]));

                    // getting section id
                    section_id=sectionDataArr[0]['id']?sectionDataArr[0]['id']:'';

                    // validating section_id
                    if(section_id!='' || !empty(section_id)){

                        //getting subject_teacher_mapping records on basis of section_id,school_id group by section_id
                        subjectTeacherMappingArr=subjectTeacherMappingTblObj->getSubjectTeacherMappingDataWhereExat(array('section_id'=>section_id,'school_id'=>_SESSION['login_detail']->school_id,'group_by'=>'section_id'));
                        //print_r(subjectTeacherMappingArr);die;
                            /*if(empty(subjectTeacherMappingArr) && count(subjectTeacherMappingArr)==0){
                                duplicate = 1;
                                errors[] = (row - 1) ." : ".rowData[6]. " - Section Does not exists.";
                                uplaodError .= "Section Does not exists.,";
                            }*/
                    }else{
                        duplicate = 1;
                        errors[] = (row - 1) ." : ".rowData[6]. " - Section is not mapped with school.To add, please contact to 'Extramarks Admin.'.";
                        uplaodError .= "Section is not mapped with school.To add, please contact to 'Extramarks Admin.'.,";
                    }

                }else{
                    duplicate = 1;
                    errors[] = (row - 1) ." : Section cannot be blank. Please check and reupload..";
                    uplaodError .= "Section cannot be blank. Please check and reupload.,";
                }
                //BOARD validation
                if (rowData[4] == '') {
                    duplicate = 1;
                    errors[] = (row - 1) . " : " . rowData[4] . " Board cannot be blank. Please check and reupload.";
                    uplaodError .= "Board cannot be blank. Please check and reupload., ";
                }



                // data=array(
                // 'username'=>post['username'],
                // 'email'=>email,
                // 'display_name'=>post['student_name'],
                // 'school_id'=>_SESSION['login_detail']->school_id,
                // 'class_id'=>class,
                // 'board_id'=>boardId,
                // 'custom_board_rack_id'=>getcustomboard,
                // 'user_type_id'=> 1,
                // 'country_id'=>99,
                // 'mobile'=>mob, 
                // 'create_time'=>date('Y-m-d H:i:s'),
                // 'ip'=>this->getClientIP(),
                // 'postalcode'=>'',
                // 'pin'=>'',
                // 'phone'=>'',
                // );




                if (rowData[4] != '') {

                    // setting board_id in data with trimmed rowData[4] 
                    data['board_id'] = trim(rowData[4]);


                    // websiteMapper = this->getServiceLocator()->get('website_mapper');
                    //where is website_mapper ?
                    // getting board_details by board_id
                    boardDetails = websiteMapper->getWebsiteCustomBoardName(data['board_id']);
                    // print_r(boardDetails);
                    boardRackId = array();
                    newboardRackId = array();

                    // looping boradDetails
                    foreach (boardDetails as boardArray) {

                        //setting rackId to boardRackId[] and customBoardId to customBoardId
                        boardRackId[] = boardArray[0]['rackId'];
                        customboardId = boardArray[0]['customBoardId'];
                    }
                    //print_r(boardRackId); echo "<pre>"; print_r(_SESSION['login_detail']->board_id);

                    // validate boardRackId
                    if (!empty(boardRackId)) {

                        // validating board_id present in boardRackId
                        if(!in_array(_SESSION['login_detail']->board_id ,boardRackId)){
                            duplicate = 1;
                            errors[] = (row - 1) . " : " . rowData[4] . " Board is not mapped with school.To change, please contact to 'Extramarks Admin.'";
                            uplaodError .= "Board is not mapped with school.To change, please contact to 'Extramarks Admin.', ";
                        }
                        // number of boardRackId must be greater that 0
                        elseif (count(boardRackId) == 0) {
                            duplicate = 1;
                            errors[] = (row - 1) . " : " . rowData[4] . " Board is not mapped with school.To change, please contact to 'Extramarks Admin.'";
                            uplaodError .= "Board is not mapped with school.To change, please contact to 'Extramarks Admin.', ";
                        }// implode(key,array) it creates string by joining araay elements by seperator as key
                             else {
                            //setting board_id of data using implode 
                            data['board_id'] = implode(',', boardRackId);
                        }
                        //print_r(data['board_id']);die;

                    }//if boardRackId is empty 
                    else {
                        // initiating seniorclassarr
                        seniorclassarr = array('VI', 'VII', 'VIII', 'IX', 'X', 'XI', 'XII', 'JEE - Main', 'JEE - Advanced', 'NEET');

                        //validating boardRackId
                        //newboardRackId = array();
                        if(count(boardRackId)>1){
                            newboardRackId[] = boardRackId[1];
                        }else{
                            newboardRackId[] = boardRackId[0];
                        }

                        //validating newboardRackId
                        if (count(newboardRackId) == 0) {
                            duplicate = 1;
                            errors[] = (row - 1) . " : " . rowData[4] . " Board is not mapped with school.To change, please contact to 'Extramarks Admin.'";
                            uplaodError .= "Board is not mapped with school.To change, please contact to 'Extramarks Admin.', ";
                        }// validating newboardRackId[0] must be equal to board_id
                        elseif(newboardRackId[0] != _SESSION['login_detail']->board_id){
                            duplicate = 1;
                            errors[] = (row - 1) . " : " . rowData[4] . " Board is not mapped with school.To change, please contact to 'Extramarks Admin.'";
                            uplaodError .= "Board is not mapped with school.To change, please contact to 'Extramarks Admin.', ";
                        }//setting board_id by newboardRackId as string seperating each element with ,
                             else {
                            data['board_id'] = implode(',', newboardRackId);
                        }
                    }
                }


                //validating class data    
                if (rowData[5] != '') {

                    //trimming and storing class_id in data array
                    data['class_id'] = trim(rowData[5]);

                    //validating board_id of data array and noardRackId
                    if (data['board_id'] != "" && count(boardRackId) > 0) {

                        //comparing board to IIT JEE
                        if (trim(board) == 'IIT JEE') {

                            //comparing class_id of data array
                            if (data['class_id'] == 'JEE - Main' || data['class_id'] == 'JEE - Advanced') {

                                // where is lms_container_service
                                //lmsServiceObj = this->getServiceLocator()->get('lms_container_service');
                                //getting class Details
                                classDetails = lmsServiceObj->getContainerInfoByName(data['class_id'], null, data['board_id']);

                                //validating count of classDetails
                                if (count(classDetails) == "0") {
                                    duplicate = 1;
                                    errors[] = (row - 1) . " : " . rowData[5] . " Class is not mapped with school board. Please contact to 'Extramarks Admin.'";
                                    uplaodError .= "Class is not mapped with school board. Please contact to 'Extramarks Admin.', ";
                                }//setting class_id of data to classDetails rackId 
                                else {
                                    data['class_id'] = classDetails->getRackId();
                                }

                            }// if(!data['class_id'] == 'JEE - Main' amd !data['class_id'] == 'JEE - Advanced')
                            else {
                                duplicate = 1;
                                errors[] = (row - 1) . " : " . rowData[5] . " Class Name Wrong";
                                uplaodError .= " Class Name Wrong, ";
                            }
                        }// if board is not IIT JEE 
                        else {

                            //getting class details by class_id of data and board_id of login_details of session
                            classDetails = lmsServiceObj->getContainerInfoByName(data['class_id'], null, _SESSION['login_detail']->board_id);

                            //validating classDetails count
                            if (count(classDetails) == "0") {
                                duplicate = 1;
                                errors[] = (row - 1) . " : " . rowData[5] . " Class is not mapped with school board. Please contact to 'Extramarks Admin.'";
                                uplaodError .= "Class is not mapped with school board. Please contact to 'Extramarks Admin.', ";
                            }//setting class_id of data to class Details rack id 
                            else {
                                data['class_id'] = classDetails->getRackId();
                            }
                        }
                    }
                }// if class_id is empty
                else{
                    duplicate = 1;
                    errors[] = (row - 1) . " : " . class . " Class cannot be blank. Please check and reupload.";
                    uplaodError .= "Class cannot be blank. Please check and reupload., ";
                }


                //valdiating school name
                if(empty(rowData[7]) || rowData[7]==''){
                    duplicate = 1;
                    errors[] = (row - 1) . " : " . rowData[1] . " School name cannot be blank. Please check and reupload.";
                    uplaodError .= "School name cannot be blank. Please check and reupload.";
                }

                    /*if(!empty(rowData[1])){
                        userDetails = tableuser->getUserByEmailAddress(rowData[1]);
                        if (empty(userDetails)) {

                        } else {
                            if (userDetails->user_id != '') {
                                duplicate = 1;
                                errors[] = (row - 1) . " : " . rowData[1] . " User email already exists";
                                uplaodError .= "User email already exists";
                            }
                        }
                    }*/




            }// end of loop for (row = 2; row <= highestRow; ++row)



            // if error
            if(duplicate== 1){
                result=array(
                        'error' => errors,
                        'success' => success,
                        'postData' => postData
                      );
                echo json_encode(result);die;
            } // if no error
            else{
                // if empcode is not empty
                if(empcode != '') {

                    //initiate dataUpload
                    dataUpload = array(
                            "uploaded_by" => empcode,
                            "uploaded_for" => empcode,
                            "total_records"=>highestRow-1,
                            "emp_code" => empcode,
                            "filename" => uploadedFileName,
                            "file_name_checksum" => checksum,
                            "auto_generated_mail" => ((auto_generated_mail == 'yes') ? 1 : 0),
                            "auto_generated_sms" => ((auto_generated_sms == 'yes') ? 1 : 0),
                            "send_otp" => ((send_otp == '1') ? 1 : 0),
                            "save_old_data" => ((save_old_data == 'yes') ? 1 : 0),
                            "status" => 0,
                            "gts_platform_type" => (isset(gts_platform_type) && gts_platform_type!= "")?gts_platform_type:NULL,
                            "file_upload_type"=>file_upload_type,
                            "upload_from"=>2
                    );


                    // excelUploadStuObj = this->getServiceLocator()->get('Assessment\Model\TstudentsregistrationexceldetailsTable');
                    // public function addUploadDetails(data)
                    //  {		
                    //     data['creation_date']=date('Y-m-d H:i:s');
                    //     this->tableMasterGateway->insert(data);	
                    //       id = this->tableMasterGateway->lastInsertValue;
                    //      return id;
                    // }
                    excelStuDetailsId = excelUploadStuObj->addUploadDetails(dataUpload);
                    //print_r(excelStuDetailsId);die;                        
                } // empcode is empty
                else {

                    //initiate dataUpload
                    dataUpload = array(
                            "uploaded_by" => this->zfcUserAuthentication()->getIdentity()->getId(),
                            "uploaded_for" => uploaded_for ,
                            "total_records"=>highestRow-1,
                            "filename" => uploadedFileName,
                            "file_name_checksum" => checksum,
                            "auto_generated_mail" => (auto_generated_mail == 'yes') ? 1 : 0,
                            "auto_generated_sms" => (auto_generated_sms == 'yes') ? 1 : 0,
                            "send_otp" => ((send_otp == '1') ? 1 : 0),
                            "save_old_data" => ((save_old_data == 'yes') ? 1 : 0),
                            "status" => 0,
                            "gts_platform_type" => (isset(gts_platform_type) && gts_platform_type!= "")?gts_platform_type:NULL,
                            "file_upload_type"=>file_upload_type,
                            "upload_from"=>2
                    );

                    // excelUploadStuObj = this->getServiceLocator()->get('Assessment\Model\TstudentsregistrationexceldetailsTable');
                    // public function addUploadDetails(data)
                    //  {		
                    //     data['creation_date']=date('Y-m-d H:i:s');
                    //     this->tableMasterGateway->insert(data);	
                    //       id = this->tableMasterGateway->lastInsertValue;
                    //      return id;
                    // }
                    excelStuDetailsId = excelUploadStuObj->addUploadDetails(dataUpload);
                }


                // what if ftpFileUpload
                // File Transfer Protocol
                // v=uploads/admin/Student-Data-Excel/+fileNameArr[0]+_+excelStuDetailsId+_+date("Y-m-d")+_request.+fileNameArr[1]
                // ftpFileUpload(path,v)

                fileUploaded = this->ftpFileUploaded(_FILES['tickerfile']['tmp_name'], 'uploads/admin/Student-Data-Excel/' . fileNameArr[0] . '_' . excelStuDetailsId . '_' . date("Y-m-d") . '_request.' . fileNameArr[1]);

                // getting highestColumn
                highestColumn = worksheet->getHighestColumn(); // e.g 'F'

                // getting index of column
                highestColumnIndex = \PHPExcel_Cell::columnIndexFromString(highestColumn);

                // ord() -> ASCII
                // nrColumns = ASCII(highestColumn)-64
                nrColumns = ord(highestColumn) - 64;


                //Autowiring Admin\Model\StudentRegistrationExcelDataTable
                studentRegistrationExcelDataTableObj = this->getServiceLocator()->get('Admin\Model\StudentRegistrationExcelDataTable');

                // outer loop 
                for (row = 2; row <= highestRow; ++row) {

                    // initiate rowData
                    rowData = array();

                    //initiate insertData
                    insertData = array();

                    // inner loop
                    for (col = 1; col < highestColumnIndex; ++col) {

                        // getting cell
                        cell = worksheet->getCellByColumnAndRow(col, row);

                        //val initiate cell value which is trimmed
                        val = trim(cell->getValue());

                        // col is date
                        if(col==22 && !empty(val)){

                            // formate data 
                            newVal = date('Y-m-d', \PHPExcel_Shared_Date::ExcelToPHP(val));

                            // if excel date is > current date update val
                            if(strtotime(newVal)>strtotime("now")){
                                val=newVal;
                            }
                        }
                        // store date in rowData
                        rowData[] = val;
                    }

//                     fname=array("Peter","Ben","Joe");
// age=array("35","37","43");
// c=array_combine(fname,age);

// o/p Array ( [Peter] => 35 [Ben] => 37 [Joe] => 43 )

// excelColumnsArray = array('name', 'email',"mobile","admission_no","board","class","section","school_name","school_code","state","city","user_country");

                    insertData = array_combine(excelColumnsArray, rowData);

                    // bool isset( var, mixed )

                    //The isset() function is an inbuilt function in PHP which checks whether a variable is set and is not NULL 
                    if(isset(insertData['package_expiry_date']) && insertData['package_expiry_date'] == ''){
                        insertData['package_expiry_date'] = NULL;
                    }
                    if(isset(insertData['paid_price']) && insertData['paid_price'] == ''){
                        insertData['paid_price'] = NULL;
                    }
                    if(isset(insertData['batch_id']) && insertData['batch_id'] == ''){
                        insertData['batch_id'] = NULL;
                    }
                    if(isset(insertData['reference_number']) && insertData['reference_number'] == ''){
                        insertData['reference_number'] = NULL;
                    }
                    insertData['upload_type'] = upload_action;
                    insertData['stakeholder'] = stakeholder;
                    insertData['register_via'] = registervia;
                    insertData['excel_details_id'] = excelStuDetailsId;
                    insertData['created_date'] = date('Y-m-d H:i:s');
                    insertData['is_uploaded'] = 0;
                    if (in_array(stakeholder,array('school_teacher'))&&  in_array(upload_action,array('school_reg'))){
                        if(!empty(school_logo)){
                            school_logoArr=explode('.',school_logo);
                            fName=school_logoArr[0];
                            extension=school_logoArr[1];
                            schoolLogoName=insertData['school_code'].'.'.extension;
                            insertData['school_logo'] = schoolLogoName;
                        }
                    }
                    studentRegistrationExcelDataTableObj->addExcelData(insertData);
                }
                condition = 'excel_details_id='.excelStuDetailsId;
                datarecord = studentRegistrationExcelDataTableObj->getStudentExcelDataDetails(condition);

                foreach(datarecord as dstas){
                    id_data[] = dstas->id;
                }

                excel_data['start_data_id'] = id_data[0];
                excel_data['end_data_id'] = end(id_data);
                excel_data['execute_status'] = 'todo';
                excelUploadStuObj->updateUploadDetails(excel_data,excelStuDetailsId);
                // Upload School Logo 
                if (in_array(stakeholder,array('school_teacher'))&&  in_array(upload_action,array('school_reg'))){
                    commonObj = this->getServiceLocator()->get("com_mapper");
                    if(!empty(schoolLogoName)){
                        fileUploaded = commonObj->ftpFileUploaded(_FILES['school_log']['tmp_name'], '/uploads/admin/go_to_school_logo/'.schoolLogoName);
                    }
                }
                success = "Please execute the uploaded files from the below queue list.";
                result=array(
                        'error' => errors,
                        'success' => success,
                        'postData' => postData
                      );
                echo json_encode(result);die;

            }
        }
    }




}
