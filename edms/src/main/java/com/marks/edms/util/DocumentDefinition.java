package com.marks.edms.util;



public class DocumentDefinition {
    public static void main(String[] args) {
//        String str = "dd.text_Field01 AS 'Location', dd.text_Field02 AS 'Industry Client Partner', dd.text_Field03 AS 'Customer Name', dd.text_Field04 AS 'Project Description', dd.text_Field05 AS 'Industry', dd.text_Field06 AS 'Currency', dd.num_Field01 AS 'TCV before VAT', dd.num_Field02 AS 'TCV before VAT(HKD)', dd.date_Field01 AS 'Expected Contract Sign Date', dd.text_Field07 AS 'Account Manager', dd.text_Field08 AS 'Solution Manager', dd.text_Field09 AS 'Project Nature', dd.num_Field03 AS 'Total Direct Variable Cost', dd.num_Field04 AS 'Total Indirect Cost', dd.text_Field10 AS 'Year of New Sales Booked Revenue', dd.num_Field05 AS 'New Sales Booked Revenue', dd.num_Field06 AS 'EBITDA : ', dd.num_Field07 AS 'EBIDTA Margin (in %) ', dd.text_Field11 AS 'Opportunity ID', dd.text_Field12 AS 'Inculding Type', dd.num_Field08 AS 'EBIDTA Guideline', dd.num_Field09 AS 'Total No. of Man-day', dd.num_Field10 AS 'No. of Offshoring and PRC-T2 Man-day', dd.num_Field11 AS 'No. of Offshoring and PRC-T2 Man-day/Total No. of Man-day', dd.text_Field13 AS 'Description for Justification Offshore <25%', dd.num_Field12 AS 'Bid Bond (GAF)', dd.num_Field13 AS 'Bid Bond (GAF)(HKD)', dd.text_Field14 AS 'Contract without  Limited Liability (CWL)', dd.text_Field15 AS 'Cash flow mismatch noted (IGC)', dd.num_Field14 AS 'Total Bcase Cost', dd.num_Field15 AS 'Labor Cost', dd.num_Field16 AS 'Labor Cost %', dd.num_Field17 AS 'Travel Expenses', dd.num_Field18 AS 'Travel Expenses%', dd.num_Field19 AS 'Misc Expenses', dd.num_Field20 AS 'Misc Expenses %', dd.num_Field21 AS '3rd Party Expenses', dd.num_Field22 AS '3rd Party Expenses %', dd.num_Field23 AS 'TCV after VAT', dd.num_Field24 AS 'TCV after VAT(HKD)', dd.text_Field16 AS 'Temporary WO application', dd.text_Field17 AS 'Pre-sales Register', dd.text_Field18 AS 'CMT With Non Group Client Only', dd.text_Field19 AS 'DCI With Design & Build Only', dd.text_Field20 AS 'DC and/or IP Deals', dd.text_Field21 AS 'Approval Type', dd.text_Field22 AS 'Region', dd.text_Field23 AS 'Fast Track Approved'";
//        String str = "dd.text_Field01 AS 'Location', dd.text_Field02 AS 'Industry Client Partner', dd.text_Field03 AS 'Customer Name', dd.text_Field04 AS 'Project Description', dd.text_Field05 AS 'Industry', dd.text_Field06 AS 'Currency', dd.num_Field01 AS 'TCV', dd.num_Field02 AS 'TCV (HKD)', dd.date_Field01 AS 'Expected Contract Sign Date', dd.text_Field07 AS 'Account Manager', dd.text_Field08 AS 'Solution Manager', dd.text_Field09 AS 'Project Nature', dd.num_Field03 AS 'Total Direct Variable Cost', dd.num_Field04 AS 'Total Indirect Cost', dd.text_Field10 AS 'Year of New Sales Booked Revenue', dd.num_Field05 AS 'New Sales Booked Revenue', dd.num_Field06 AS 'EBITDA', dd.num_Field07 AS 'EBITDA Margin (in %) ', dd.text_Field11 AS 'Opportunity ID', dd.num_Field08 AS 'EBITDA Guideline', dd.num_Field09 AS 'Total No. of Man-day', dd.num_Field10 AS 'No. of Man-day Offshore', dd.num_Field11 AS 'No. of Man-day Offshore/Total No. of Man-day(in %)', dd.text_Field12 AS 'Justification for offshoring < 40%', dd.text_Field13 AS 'DC and/or IP Deals', dd.text_Field14 AS 'Pre-sales Register', dd.text_Field15 AS 'CMT With Non Group Client Only', dd.text_Field16 AS 'DCI With Design & Build Only', dd.text_Field17 AS 'Require CWL application & approval', dd.text_Field18 AS 'Cash flow mismatch noted (IGC)', dd.text_Field19 AS 'Approval Type', dd.text_Field20 AS 'Fast Track Approved', dd.text_Field21 AS 'End customer territory', dd.text_Field22 AS 'Led by', dd.num_Field12 AS 'EBIDTA Margin for Approval (in %)'";
//        String str = "dd.text_Field01 AS 'Location', dd.text_Field02 AS 'Industry Client Partner', dd.text_Field03 AS 'Customer Name', dd.text_Field04 AS 'Project Description', dd.text_Field05 AS 'Industry', dd.text_Field06 AS 'Currency', dd.num_Field01 AS 'TCV', dd.num_Field02 AS 'TCV (HKD)', dd.date_Field01 AS 'Expected Contract Sign Date', dd.text_Field07 AS 'Account Manager', dd.text_Field08 AS 'Solution Manager', dd.text_Field09 AS 'Project Nature', dd.num_Field03 AS 'Total Direct Variable Cost', dd.num_Field04 AS 'Total Indirect Cost', dd.text_Field10 AS 'Year of New Sales Booked Revenue', dd.num_Field05 AS 'New Sales Booked Revenue', dd.num_Field06 AS 'EBITDA : ', dd.num_Field07 AS 'EBIDTA Margin (in %) ', dd.text_Field11 AS 'Opportunity ID', dd.text_Field12 AS 'Contract without Limited Liability (CWL)', dd.text_Field13 AS 'Cash flow mismatch noted (IGC)'";
        String str_637 = "dd.text_Field01 AS 'Location', dd.text_Field02 AS 'Industry Client Partner', dd.text_Field03 AS 'Customer Name', dd.text_Field04 AS 'Project Description', dd.text_Field05 AS 'Industry', dd.text_Field06 AS 'Currency', dd.num_Field01 AS 'TCV', dd.num_Field02 AS 'TCV (HKD)', dd.date_Field01 AS 'Expected Contract Sign Date', dd.text_Field07 AS 'Account Manager', dd.text_Field08 AS 'Solution Manager', dd.text_Field09 AS 'Project Nature', dd.num_Field03 AS 'Total Direct Variable Cost', dd.num_Field04 AS 'Total Indirect Cost', dd.text_Field10 AS 'Year of New Sales Booked Revenue', dd.num_Field05 AS 'New Sales Booked Revenue', dd.num_Field06 AS 'EBITDA', dd.num_Field07 AS 'EBITDA Margin (in %) ', dd.text_Field11 AS 'Opportunity ID', dd.num_Field08 AS 'EBITDA Guideline', dd.num_Field09 AS 'Total No. of Man-day', dd.num_Field10 AS 'No. of Man-day Offshore', dd.num_Field11 AS 'No. of Man-day Offshore/Total No. of Man-day(in %)', dd.text_Field12 AS 'Justification for offshoring < 40%', dd.text_Field13 AS 'DC and/or IP Deals', dd.text_Field14 AS 'Pre-sales Register', dd.text_Field15 AS 'CMT With Non Group Client Only', dd.text_Field16 AS 'DCI With Design & Build Only', dd.text_Field17 AS 'Require CWL application & approval', dd.text_Field18 AS 'Cash flow mismatch noted (IGC)', dd.text_Field19 AS 'Approval Type', dd.text_Field20 AS 'Fast Track Approved', dd.text_Field21 AS 'End customer territory', dd.text_Field22 AS 'Led by', dd.num_Field12 AS 'EBIDTA Margin for Approval (in %)'";
        String str_638 = "dd.text_Field01 AS 'Location', dd.text_Field02 AS 'Industry Client Partner', dd.text_Field03 AS 'Customer Name', dd.text_Field04 AS 'Project Description', dd.text_Field05 AS 'Industry', dd.text_Field06 AS 'Currency', dd.num_Field01 AS 'TCV', dd.num_Field02 AS 'TCV (HKD)', dd.date_Field01 AS 'Expected Contract Sign Date', dd.text_Field07 AS 'Account Manager', dd.text_Field08 AS 'Solution Manager', dd.text_Field09 AS 'Project Nature', dd.num_Field03 AS 'Total Direct Variable Cost', dd.num_Field04 AS 'Total Indirect Cost', dd.text_Field10 AS 'Year of New Sales Booked Revenue', dd.num_Field05 AS 'New Sales Booked Revenue', dd.num_Field06 AS 'EBITDA : ', dd.num_Field07 AS 'EBIDTA Margin (in %) ', dd.text_Field11 AS 'Opportunity ID', dd.text_Field12 AS 'Contract without Limited Liability (CWL)', dd.text_Field13 AS 'Cash flow mismatch noted (IGC)'";

        String str_625 = "dd.text_Field01 AS 'Business Case eDMS Document ID', dd.text_Field02 AS 'Customer Name', dd.text_Field03 AS 'Project Description', dd.num_Field01 AS 'TCV Before VAT(HKD)', dd.text_Field05 AS 'Works Order Number (Assigned by Finance)', dd.text_Field07 AS 'Currency', dd.num_Field03 AS 'New Sales Booked Revenue', dd.text_Field04 AS 'Customer sector', dd.text_Field06 AS 'New Business Initiatives?', dd.text_Field08 AS 'NBI', dd.text_Field09 AS 'Year of New Sales Booked Revenue', dd.text_Field10 AS 'Opportunity ID', dd.text_Field11 AS 'Wo Type', dd.text_Field12 AS 'Capabilities', dd.date_Field01 AS 'Contract Start Date', dd.date_Field02 AS 'Contract End Date', dd.date_Field03 AS 'Dlp End Date', dd.text_Field13 AS 'Internal / External  ', dd.text_Field14 AS 'End Customer Name', dd.num_Field02 AS 'EBIDTA Margin (in %)', dd.text_Field15 AS 'CCC', dd.text_Field16 AS 'Project Manager', dd.text_Field17 AS 'OU Name', dd.text_Field18 AS 'SPA Industry', dd.text_Field19 AS 'Contract Ref No.', dd.text_Field20 AS 'Region', dd.num_Field04 AS 'TCV After VAT(HKD)', dd.num_Field05 AS 'TCV before VAT (RMB)', dd.num_Field06 AS 'TCV after VAT (RMB)', dd.text_Field21 AS 'Fast Track Approved', dd.text_Field22 AS 'Announcement Industry', dd.text_Field23 AS 'Indsutry Client Partner', dd.text_Field24 AS 'End customer standardized name(Assigned by Finance)', dd.text_Field25 AS 'VO (Y/N)', dd.text_Field26 AS 'Beneficiary Group'";

        String str_360 = "dd.text_Field01 AS 'Business Case eDMS Document ID', dd.text_Field02 AS 'Customer Name', dd.text_Field03 AS 'Project Description', dd.num_Field01 AS 'TCV(HKD)', dd.text_Field05 AS 'Works Order Number (Assigned by Finance)', dd.text_Field07 AS 'Currency', dd.num_Field03 AS 'New Sales Booked Revenue', dd.text_Field04 AS 'Customer sector', dd.text_Field06 AS 'New Business Initiatives?', dd.text_Field08 AS 'NBI', dd.text_Field09 AS 'Year of New Sales Booked Revenue', dd.text_Field10 AS 'Opportunity ID', dd.text_Field11 AS 'Wo Type', dd.text_Field12 AS 'Delivery / SO', dd.date_Field01 AS 'Contract Start Date', dd.date_Field02 AS 'Contract End Date', dd.date_Field03 AS 'Dlp End Date', dd.text_Field13 AS 'Internal/External (External party)', dd.text_Field14 AS 'End Customer Name', dd.num_Field02 AS 'EBIDTA Margin (in %)', dd.text_Field15 AS 'CCC', dd.text_Field16 AS 'Project Manager', dd.text_Field17 AS 'OU Name', dd.text_Field18 AS 'SPA Industry', dd.text_Field19 AS 'Contract Ref No.', dd.num_Field04 AS 'TCV (Foreign Currency)', dd.text_Field20 AS 'Time based Calculation Method', dd.text_Field21 AS 'Project Director', dd.date_Field04 AS 'Date of Kick-of/handover meeting with PM', dd.text_Field22 AS 'Handover Checklist', dd.date_Field05 AS 'Date of Business Case Approval', dd.text_Field23 AS 'Final Business Case Approver', dd.text_Field24 AS 'Major/Minor', dd.text_Field25 AS 'Solution Manager', dd.text_Field26 AS 'Prime Delivery Practice Leader / SO Lead', dd.text_Field27 AS 'Involve Intenal Labour', dd.text_Field28 AS 'New Sales/Renewal', dd.text_Field29 AS 'New Sales Quarter', dd.text_Field30 AS 'Internal/External (Finance use)', dd.date_Field06 AS 'Project Start Date', dd.date_Field07 AS 'Project End Date', dd.text_Field31 AS 'New Sales(Y/N)', dd.date_Field08 AS 'WO System Close Date', dd.text_Field32 AS 'Temporary Works Order', dd.text_Field33 AS 'Involve DCO service?', dd.text_Field34 AS 'Fast Track Approved', dd.text_Field35 AS 'Standard Price Book', dd.text_Field36 AS 'Industry Client Partner', dd.text_Field37 AS 'Project Initiation Form (PIF) Link', dd.text_Field38 AS 'Announcement Industry', dd.text_Field39 AS 'End customer standardized name(Assigned by Finance)', dd.text_Field40 AS 'VO (Y/N)', dd.text_Field41 AS 'Beneficiary Group'";

        String str_639 = "dd.text_Field01 AS 'Location', dd.text_Field02 AS 'Industry Client Partner', dd.text_Field03 AS 'Customer Name', dd.text_Field04 AS 'Project Description', dd.text_Field05 AS 'Industry', dd.text_Field06 AS 'Currency', dd.num_Field01 AS 'TCV before VAT', dd.num_Field02 AS 'TCV before VAT(HKD)', dd.date_Field01 AS 'Expected Contract Sign Date', dd.text_Field07 AS 'Account Manager', dd.text_Field08 AS 'Solution Manager', dd.text_Field09 AS 'Project Nature', dd.num_Field03 AS 'Total Direct Variable Cost', dd.num_Field04 AS 'Total Indirect Cost', dd.text_Field10 AS 'Year of New Sales Booked Revenue', dd.num_Field05 AS 'New Sales Booked Revenue', dd.num_Field06 AS 'EBITDA : ', dd.num_Field07 AS 'EBIDTA Margin (in %) ', dd.text_Field11 AS 'Opportunity ID', dd.text_Field12 AS 'Inculding Type', dd.num_Field08 AS 'EBIDTA Guideline', dd.num_Field09 AS 'Total No. of Man-day', dd.num_Field10 AS 'No. of Offshoring and PRC-T2 Man-day', dd.num_Field11 AS 'No. of Offshoring and PRC-T2 Man-day/Total No. of Man-day', dd.text_Field13 AS 'Description for Justification Offshore <25%', dd.num_Field12 AS 'Bid Bond (GAF)', dd.num_Field13 AS 'Bid Bond (GAF)(HKD)', dd.text_Field14 AS 'Contract without  Limited Liability (CWL)', dd.text_Field15 AS 'Cash flow mismatch noted (IGC)', dd.num_Field14 AS 'Total Bcase Cost', dd.num_Field15 AS 'Labor Cost', dd.num_Field16 AS 'Labor Cost %', dd.num_Field17 AS 'Travel Expenses', dd.num_Field18 AS 'Travel Expenses%', dd.num_Field19 AS 'Misc Expenses', dd.num_Field20 AS 'Misc Expenses %', dd.num_Field21 AS '3rd Party Expenses', dd.num_Field22 AS '3rd Party Expenses %', dd.num_Field23 AS 'TCV after VAT', dd.num_Field24 AS 'TCV after VAT(HKD)', dd.text_Field16 AS 'Temporary WO application', dd.text_Field17 AS 'Pre-sales Register', dd.text_Field18 AS 'CMT With Non Group Client Only', dd.text_Field19 AS 'DCI With Design & Build Only', dd.text_Field20 AS 'DC and/or IP Deals', dd.text_Field21 AS 'Approval Type', dd.text_Field22 AS 'Region', dd.text_Field23 AS 'Fast Track Approved'";

        String str_546 = "dd.date_Field01 AS 'DATE of Application', dd.text_Field01 AS 'NAME of applicant', dd.text_Field02 AS 'Number of employees', dd.text_Field03 AS 'CCC', dd.text_Field04 AS 'POSITION', dd.text_Field05 AS 'Departments Regions', dd.text_Field06 AS 'Mobile number', dd.text_Field07 AS 'Project 1', dd.text_Field08 AS 'Customer NAME', dd.text_Field09 AS 'Project Summary', dd.date_Field02 AS 'Event START TIME', dd.date_Field03 AS 'Event COMPLETION TIME', dd.text_Field10 AS 'Risk Analysis', dd.num_Field01 AS 'Project 1 Budget Amount (10K)', dd.num_Field02 AS 'Project 2 Budget Amount (10K)', dd.num_Field03 AS 'Project 3 Budget Amount (10K)', dd.text_Field11 AS 'Sales Manager', dd.text_Field12 AS 'Reasons', dd.text_Field13 AS 'Description / No. of Technical Exchange Meetings', dd.text_Field14 AS 'Cost (budget)', dd.text_Field15 AS 'Description FOR Other Costs', dd.num_Field04 AS 'No. of Applications (FOR the same project)', dd.num_Field05 AS 'Transportation Budget Amount (pay BY staff)', dd.num_Field06 AS 'Transportation Budget Amount (pay BY company)', dd.num_Field07 AS 'Accommodation Budget Amount (pay BY staff)', dd.num_Field08 AS 'Accommodation Budget Amount (pay BY company)', dd.num_Field09 AS 'LOCAL Transportation Budget Amount (taxi / bus / metro)', dd.num_Field10 AS 'Travel Subsidy Budget Amount (pay BY company)', dd.num_Field11 AS 'Business Entertainment Budget Amount', dd.num_Field12 AS 'Business Gifts Budget Amount (only FOR Sales)', dd.num_Field13 AS 'Presales Overtime Budget Amount', dd.num_Field14 AS 'Other Budget Amount', dd.num_Field15 AS 'Total Budget Amount', dd.text_Field16 AS 'Project 2', dd.text_Field17 AS 'Project 3', dd.num_Field16 AS 'No. of Recipients of Entertainment', dd.num_Field17 AS 'No. of Recipients of Gift', dd.text_Field18 AS 'Itinerary'";

        String str_547 ="dd.text_Field01 AS 'NAME of applicant', dd.text_Field02 AS 'Number of employees', dd.text_Field03 AS 'CCC', dd.text_Field04 AS 'POSITION', dd.text_Field05 AS 'Departments, Regions', dd.text_Field06 AS 'Mobile number', dd.text_Field07 AS 'Project 1 (Same AS application form)', dd.text_Field08 AS 'Customer NAME', dd.num_Field01 AS 'Project Budget Amount (10K)', dd.text_Field09 AS 'A Project Description (Background, STATUS)', dd.text_Field10 AS 'A NAME AND Department of the Contact Person (Impact TO the project)', dd.text_Field11 AS 'A Expected Target AND Result of this Visit', dd.text_Field12 AS 'Documents Exchanged WITH Clients', dd.num_Field02 AS 'Transportation Budget Amount (pay BY staff)', dd.num_Field03 AS 'Transportation Budget Amount (pay BY company)', dd.num_Field04 AS 'Accommodation Budget Amount (pay BY staff)', dd.num_Field05 AS 'Accommodation Budget Amount (pay BY company)', dd.num_Field06 AS 'LOCAL Transportation Budget Amount (taxi / bus / metro)', dd.num_Field07 AS 'Travel Subsidy Budget Amount (pay BY company)', dd.num_Field08 AS 'Business Entertainment Budget Amount', dd.num_Field09 AS 'Business Gifts Budget Amount (only FOR Sales)', dd.num_Field10 AS 'Presales Overtime Budget Amount', dd.num_Field11 AS 'Other Budget Amount', dd.num_Field12 AS 'Total Budget Amount', dd.text_Field13 AS 'Detail Description', dd.text_Field14 AS 'Presales Expenses Request Document ID No.', dd.date_Field01 AS 'A Customer Contact Report DATE (FROM)', dd.date_Field02 AS 'B Customer Contact Report DATE (FROM)', dd.text_Field15 AS 'B Project Description (Background, STATUS)', dd.text_Field16 AS 'B NAME AND Department of the Contact Person (Impact TO the project)', dd.text_Field17 AS 'B Expected Target AND Result of this Visit', dd.date_Field03 AS 'C Customer Contact Report DATE (FROM)', dd.text_Field18 AS 'C Project Description (Background, STATUS)', dd.text_Field19 AS 'C NAME AND Department of the Contact Person (Impact TO the project)', dd.text_Field20 AS 'C Expected Target AND Result of this Visit', dd.date_Field04 AS 'A Customer Contact Report DATE (TO)', dd.date_Field05 AS 'B Customer Contact Report DATE (TO)', dd.date_Field06 AS 'C Customer Contact Report DATE (TO)', dd.text_Field21 AS 'Project 2 (Same AS application form)', dd.text_Field22 AS 'Project 3 (Same AS application form)', dd.num_Field13 AS 'No. of Recipients of Entertainment', dd.num_Field14 AS 'No. of Recipients of Gift', dd.text_Field23 AS 'Itinerary'";
        String str_548 ="dd.date_Field01 AS 'DATE of Application', dd.text_Field01 AS 'NAME of applicant', dd.text_Field02 AS 'Number of employee', dd.text_Field03 AS 'CCC', dd.text_Field04 AS 'POSITION', dd.text_Field05 AS 'Departments, Regions', dd.text_Field06 AS 'Mobile number', dd.text_Field07 AS 'Project 1', dd.text_Field08 AS 'Customer NAME', dd.text_Field09 AS 'Project Summary', dd.date_Field02 AS 'Event START TIME', dd.date_Field03 AS 'Event COMPLETION TIME', dd.text_Field10 AS 'Risk Analysis', dd.num_Field01 AS 'Project 1 Budget Amount (10K)', dd.num_Field02 AS 'Project 2 Budget Amount (10K)', dd.num_Field03 AS 'Project 3 Budget Amount (10K)', dd.text_Field11 AS 'Sales Manager', dd.text_Field12 AS 'Reasons', dd.text_Field13 AS 'Description / No. of Technical Exchange Meetings', dd.text_Field14 AS 'Cost (budget)', dd.text_Field15 AS 'Description FOR Other Costs', dd.num_Field04 AS 'No. of Applications (FOR the same project)', dd.num_Field05 AS 'Transportation Budget Amount (pay BY staff)', dd.num_Field06 AS 'Transportation Budget Amount (pay BY company)', dd.num_Field07 AS 'Accommodation Budget Amount (pay BY staff)', dd.num_Field08 AS 'Accommodation Budget Amount (pay BY company)', dd.num_Field09 AS 'LOCAL Transportation Budget Amount (taxi / bus / metro)', dd.num_Field10 AS 'Travel Subsidy Budget Amount (pay BY company)', dd.num_Field11 AS 'Business Entertainment Budget Amount', dd.num_Field12 AS 'Business Gifts Budget Amount (only FOR Sales)', dd.num_Field13 AS 'Presales Overtime Budget Amount', dd.num_Field14 AS 'Other Budget Amount', dd.num_Field15 AS 'Total Budget Amount', dd.text_Field16 AS 'Project 2', dd.text_Field17 AS 'Project 3', dd.num_Field16 AS 'No. of Recipients of Entertainment', dd.num_Field17 AS 'No. of Recipients of Gift', dd.text_Field18 AS 'Itinerary'";
        String str_549 ="dd.text_Field01 AS 'NAME of applicant', dd.text_Field02 AS 'Number of employees', dd.text_Field03 AS 'CCC', dd.text_Field04 AS 'POSITION', dd.text_Field05 AS 'Departments, Regions', dd.text_Field06 AS 'Mobile number', dd.text_Field07 AS 'Project 1 (Same AS application form)', dd.text_Field08 AS 'Customer NAME', dd.num_Field01 AS 'Project Budget Amount (10K)', dd.text_Field09 AS 'A Project Description (Background, STATUS)', dd.text_Field10 AS 'A NAME AND Department of the Contact Person (Impact TO the project)', dd.text_Field11 AS 'A Expected Target AND Result of this Visit', dd.text_Field12 AS 'Documents Exchanged WITH Clients', dd.num_Field02 AS 'Transportation Budget Amount (pay BY staff)', dd.num_Field03 AS 'Transportation Budget Amount (pay BY company)', dd.num_Field04 AS 'Accommodation Budget Amount (pay BY staff)', dd.num_Field05 AS 'Accommodation Budget Amount (pay BY company)', dd.num_Field06 AS 'LOCAL Transportation Budget Amount (taxi / bus / metro)', dd.num_Field07 AS 'Travel Subsidy Budget Amount (pay BY company)', dd.num_Field08 AS 'Business Entertainment Budget Amount', dd.num_Field09 AS 'Business Gifts Budget Amount (only FOR Sales)', dd.num_Field10 AS 'Presales Overtime Budget Amount', dd.num_Field11 AS 'Other Budget Amount', dd.num_Field12 AS 'Total Budget Amount', dd.text_Field13 AS 'Detail Description', dd.text_Field14 AS 'Presales Expenses Request Document ID No.', dd.date_Field01 AS 'A Customer Contact Report DATE (FROM)', dd.date_Field02 AS 'B Customer Contact Report DATE (FROM)', dd.text_Field15 AS 'B Project Description (Background, STATUS)', dd.text_Field16 AS 'B NAME AND Department of the Contact Person (Impact TO the project)', dd.text_Field17 AS 'B Expected Target AND Result of this Visit', dd.date_Field03 AS 'C Customer Contact Report DATE (FROM)', dd.text_Field18 AS 'C Project Description (Background, STATUS)', dd.text_Field19 AS 'C NAME AND Department of the Contact Person (Impact TO the project)', dd.text_Field20 AS 'C Expected Target AND Result of this Visit', dd.date_Field04 AS 'A Customer Contact Report DATE (TO)', dd.date_Field05 AS 'B Customer Contact Report DATE (TO)', dd.date_Field06 AS 'C Customer Contact Report DATE (TO)', dd.text_Field21 AS 'Project 2 (Same AS application form)', dd.text_Field22 AS 'Project 3 (Same AS application form)', dd.num_Field13 AS 'No. of Recipients of Gift', dd.num_Field14 AS 'No. of Recipients of Entertainment ', dd.text_Field23 AS 'Itinerary'";

        String str_641 ="dd.text_Field01 AS 'Traveller's Name', dd.text_Field02 AS 'Staff Number', dd.text_Field03 AS 'Traveler's Tel No.', dd.text_Field04 AS 'Cost Center Code', dd.text_Field05 AS 'Works Order Number', dd.text_Field06 AS 'Works Order Project Name', dd.text_Field07 AS 'Itinerary (Route of Trip)', dd.date_Field01 AS 'Trip Start Date', dd.date_Field02 AS 'Trip End Date', dd.num_Field01 AS 'Transportation Budget Amount (pay BY staff)', dd.num_Field02 AS 'Accommodation Budget Amount (pay BY staff)', dd.num_Field03 AS 'LOCAL Transportation Budget Amount (taxi / bus / metro)', dd.num_Field04 AS 'Business Entertainment Budget Amount', dd.num_Field05 AS 'Transportation Budget Amount (pay BY company)', dd.num_Field06 AS 'Accommodation Budget Amount (pay BY company)', dd.num_Field07 AS 'Travel Subsidy Budget Amount (pay BY company)', dd.num_Field08 AS 'Total Budget Amount', dd.text_Field08 AS 'Business Unit / Branch / Section', dd.text_Field09 AS 'Currency', dd.num_Field09 AS 'Total Expenses (Actual HK$)', dd.text_Field10 AS 'Request Type', dd.num_Field10 AS 'No. of Recipients of Entertainment', dd.num_Field11 AS 'Other Budget Amount'";

        String str_442 ="dd.text_Field01 AS 'Applicant Name', dd.text_Field02 AS 'Staff Number', dd.text_Field03 AS 'Business Unit / Branch / Section', dd.text_Field04 AS 'CCC', dd.text_Field05 AS 'Works Order Number', dd.text_Field06 AS 'Recipient', dd.date_Field01 AS 'Expected Date of Payment', dd.date_Field02 AS 'Actual Date of Payment', dd.text_Field07 AS 'Payment Item 1', dd.num_Field01 AS 'Amount 1 (RMB)', dd.text_Field08 AS 'Payment Item 2', dd.num_Field02 AS 'Amount 2 (RMB)', dd.text_Field09 AS 'Payment Item 3', dd.num_Field03 AS 'Amount 3 (RMB)', dd.text_Field10 AS 'Payment Item 4', dd.num_Field04 AS 'Amount 4 (RMB)', dd.text_Field11 AS 'Payment Item 5', dd.num_Field05 AS 'Amount 5 (RMB)', dd.num_Field06 AS 'Total Amount', dd.text_Field12 AS 'Currency', dd.text_Field13 AS 'Entity'";
        String str_474 = "dd.text_Field02 AS 'Purchase ORDER No.', dd.text_Field03 AS 'NAME of Applicant', dd.text_Field04 AS 'Staff No. of Applicant', dd.text_Field05 AS 'Department', dd.text_Field06 AS 'Phone No.', dd.text_Field07 AS 'The FULL NAME of the Supplier', dd.text_Field08 AS 'Supporting Sales Contract No.', dd.text_Field09 AS 'Supporting Sales Contract NAME', dd.text_Field10 AS 'Product 1 NAME', dd.num_Field01 AS 'Product 1 Price (RMB)', dd.num_Field02 AS 'Product 1 Quantity', dd.text_Field11 AS 'Product 2 NAME', dd.num_Field03 AS 'Product 2 Price (RMB)', dd.num_Field04 AS 'Product 2 Quantity', dd.text_Field12 AS 'Product 3 NAME', dd.num_Field05 AS 'Product 3 Price (RMB)', dd.num_Field06 AS 'Product 3 Quantity', dd.num_Field07 AS 'Total amount (RMB)', dd.text_Field01 AS 'Entity'";
        String str_374 = "dd.text_Field01 AS 'Name of Applicant', dd.text_Field02 AS 'Business Unit / Branch / Section', dd.text_Field03 AS 'Company Name of Payer', dd.text_Field04 AS 'Company Name of Beneficiary', dd.num_Field01 AS 'Amount Payable', dd.text_Field05 AS 'Payment Details and PO Payment Schedule', dd.text_Field06 AS 'Sales Contract Ref. No.', dd.text_Field07 AS 'Project Name', dd.text_Field08 AS 'Customer Name', dd.text_Field09 AS 'P.O. No.', dd.text_Field10 AS 'Payment Received (Raito / Amount)', dd.text_Field11 AS 'Payment Paid (Raito / Amount)', dd.text_Field12 AS 'To pay this time (Raito/ Amount)', dd.text_Field13 AS 'Cash Flow Mismatch', dd.text_Field14 AS 'Cost Center', dd.text_Field15 AS 'Works Order Number', dd.text_Field16 AS 'Currency', dd.text_Field17 AS 'Entity'";


        String str_476 = "dd.num_Field01 AS 'Total Amount (RMB)', dd.num_Field02 AS 'Total Amount (HK$)', dd.text_Field01 AS 'Works Order Number', dd.text_Field02 AS 'Contract Name', dd.text_Field03 AS 'Contracting Party'";
        String str_372 = "dd.text_Field01 AS 'Customer Name', dd.text_Field02 AS 'Project Description', dd.num_Field01 AS 'TCV (RMB)', dd.text_Field03 AS 'Account Manager', dd.text_Field04 AS 'Entity', dd.text_Field05 AS 'CWL', dd.text_Field06 AS 'GAF', dd.text_Field07 AS 'IGC', dd.text_Field08 AS 'Cash Flow Mismatch', dd.text_Field09 AS 'TWO'";

        char[] chars_637 = str_637.toCharArray();
        char[] chars_625 = str_625.toCharArray();
        char[] chars_360 = str_360.toCharArray();
        char[] chars_639 = str_639.toCharArray();
        char[] chars_638 = str_638.toCharArray();

        char[] chars_546 = str_546.toCharArray();
        char[] chars_547 = str_547.toCharArray();
        char[] chars_548 = str_548.toCharArray();
        char[] chars_549 = str_549.toCharArray();
        char[] chars_641 = str_641.toCharArray();
        char[] chars_442 = str_442.toCharArray();
        char[] chars_474 = str_474.toCharArray();
        char[] chars_374 = str_374.toCharArray();
        char[] chars_476 = str_476.toCharArray();
        char[] chars_372 = str_372.toCharArray();
//        printSomething(chars_637);
//        printSomething(chars_638);
//        printSomething(chars_639);
//        printSomething(chars_625);
//        printSomething(chars_474);
        printSomething(chars_374);
//        printSomething(chars_476);
//        printSomething(chars_372);
//        printSomething(chars_360);
//        printSomething(chars_546);
//        printSomething(chars_547);
//        printSomething(chars_548);
//        printSomething(chars_549);
//        printSomething(chars_641);

    }

    private static void printSomething(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
            if (chars[i] == ',') {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("========end=======================");
    }
}
