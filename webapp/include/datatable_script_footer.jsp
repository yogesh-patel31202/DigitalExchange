<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/jszip.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/pdfmake.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/vfs_fonts.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/buttons.html5.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/buttons.print.min.js"></script>
<script type="text/javascript"
	src="assets/vendor/data table/js/buttons.colVis.min.js"></script>
	
<!-- Merge and Customize Script Start -->
<script>
var page_title = document.getElementById('page_title').innerHTML ? document.getElementById('page_title').value  : "";
var print_name = document.getElementById('print_name') ? document.getElementById('print_name').value  : "";
var first_date1 = document.getElementById('d1') ? document.getElementById('d1').value  : "";
var second_date1 = document.getElementById('d2') ? document.getElementById('d2').value  : "";
var bank_name = document.getElementById('bank_name') ? document.getElementById('bank_name').innerHTML  : "";
var print_balance = document.getElementById('print_balance') ? document.getElementById('print_balance').value : "";
var print_due = document.getElementById('print_due') ? document.getElementById('print_due').value : "";
var month =document.getElementById('selected_month') ? document.getElementById('selected_month').innerHTML : "";
var year= document.getElementById('year') ? document.getElementById('year').value : "";

//alert(print_due);


function convertDate(dateString){
	  var p = dateString.split(/\D/g)
	  var con_date = [p[2],p[1],p[0] ].join("-")
	  if(con_date == "--"){
		  return ""
	  }else{
		  return con_date
	  }
	}
	
	
function printfunction() {
	//alert(name);
	//alert(print_balance);
	first_date = convertDate(first_date1);
	second_date = convertDate(second_date1);
	
	var page_title_tx = '';
	var date_tx = '';
	var balance_tx = '';
	var bank_tx = '';
	var print_name_tx = '';
	var month_year_tx = '';
	var print_due_tx = '';
	
	if(page_title!=""){
		page_title_tx = '<h1 style="text-align:center;margin-bottom:10px;">\n' + page_title + '</h1>';
	}
	if(first_date!=""){
		date_tx = '<span style="text-align:center;"> From Date: '+ first_date +',</span> ';
	}
	if(second_date!=""){
		date_tx = '<span style="text-align:center;">To Date: '+ second_date + ',</span> ';
	}
	if(first_date!="" && second_date!=""){
		date_tx = '<span style="text-align:center;"> From Date: '+ first_date + ' To Date: '+ second_date +',</span> ';
	}
	if(print_balance!=""){
		balance_tx = '<span style="text-align:center;">Pre Balance: ' + print_balance + ',</span> ';
	}
	if(print_due!=""){
		print_due_tx = '<span style="text-align:center;">Due Amount: ' + print_due + ',</span> ';
	}
	if(bank_name!=""){
		bank_tx = '<span style="text-align:center;">Bank Name: ' + bank_name + ',</span> ';
	}
	if(print_name!=""){
		print_name_tx = '<span style="text-align:center;">Name: '+ print_name +',</span> ';
	}
	if(month!="" || year!=""){
		month_year_tx = '<span style="text-align:center;margin-bottom:10px;">'+"("+month+"/"+year+")"+',</span> ';
	}
	//alert(page_title_tx + '<p style="text-align:center;">'+ date_tx + balance_tx + bank_tx + name_tx + month_year_tx + '</p>');
	return page_title_tx + '<p style="text-align:center;">'+ print_name_tx + date_tx + balance_tx + print_due_tx + bank_tx + month_year_tx + '</p>';
}

function printTitle() {
    return page_title;
}

function printOtherfunction() {
	//alert(name);
	//alert(print_balance);
	first_date = convertDate(first_date1);
	second_date = convertDate(second_date1);
	
	var page_title_tx = '';
	var date_tx = '';
	var balance_tx = '';
	var bank_tx = '';
	var print_name_tx = '';
	var month_year_tx = '';
	var print_due_tx = '';
	
	if(page_title!=""){
		page_title_tx = page_title + ' ';
	}
	if(first_date!=""){
		date_tx = 'From Date :'+ first_date +' ';
	}
	if(second_date!=""){
		date_tx = 'To Date: '+ second_date + ' ';
	}
	if(first_date!="" && second_date!=""){
		date_tx = 'From Date :'+ first_date + ' To Date: '+ second_date + ' ';
	}
	if(print_balance!=""){
		balance_tx = 'Pre Balance: ' + print_balance + ' ';
	}
	if(print_due!=""){
		print_due_tx = 'Due Amount: ' + print_due + ' ';
	}
	if(bank_name!=""){
		bank_tx = 'Bank Name: ' + bank_name + ' ';
	}
	if(print_name!=""){
		print_name_tx = 'Name: '+ print_name +' ';
	}
	if(month!="" || year!=""){
		month_year_tx = 'Month :' + month + 'Year :' +year+' ';
	}
	//alert(page_title_tx + '<p style="text-align:center;">'+ date_tx + balance_tx + bank_tx + name_tx + month_year_tx + '</p>');
	return print_name_tx + date_tx + balance_tx + bank_tx + print_due_tx + month_year_tx;
}


function initializeDataTable(selector, scrollX, customMessageTopFunction) {
    $(document).ready(function () {
        var messageTop = "";

        if (customMessageTopFunction) {
            messageTop = customMessageTopFunction();
        }

        $(selector).DataTable({
            dom: 'lBfrtip',
            buttons: [
                {
                    extend: 'print',
                    footer: true,
                    title: '',
                    messageTop: printfunction(),
                    exportOptions: {
                        columns: ':visible'
                    }
                },
                {
                    extend: 'excel',
                    footer: true,
                    title: printTitle(),
                    messageTop: messageTop,
                    exportOptions: {
                        columns: ':visible'
                    }
                },
                {
                    extend: 'pdf',
                    footer: true,
                    title: printTitle(),
                    messageTop: messageTop,
                    exportOptions: {
                        columns: ':visible'
                    }
                },
                'colvis',
            ],
            lengthMenu: [
                [20, 50, 100, 500, -1],
                ['20', '50', '100', '500', 'all']
            ],
            pagingType: 'full_numbers',
            responsive: true,
            scrollX: scrollX
        });
    });
}

initializeDataTable('#large_simple_table', true);
initializeDataTable('#small_simple_table', false );
initializeDataTable('#large_table', true, printOtherfunction);
initializeDataTable('#small_table', false, printOtherfunction);

</script>
<!-- Merge and Customize Script End -->
