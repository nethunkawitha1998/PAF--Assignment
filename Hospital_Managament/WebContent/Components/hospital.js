$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide(); }); 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateItemForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "HospitalAPI",  
		type : type,  
		data : $("#formItem").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onItemSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onItemSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divItemsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidItemIDSave").val("");  
	$("#formItem")[0].reset(); 
} 
 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());     
	$("#hospitalCode").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#hospitalName").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#hospitalEmail").val($(this).closest("tr").find('td:eq(2)').text());
	$("#hospitalDesc").val($(this).closest("tr").find('td:eq(3)').text());
	$("#hospitalDistrict").val($(this).closest("tr").find('td:eq(4)').text());
	$("#hospitalTel").val($(this).closest("tr").find('td:eq(5)').text()); 
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
		{  
			$.ajax(  
			{   
				url : "HospitalAPI",   
				type : "DELETE",   
				data : "hospitalID=" + $(this).data("hospitalID"),   
				dataType : "text",   
				complete : function(response, status)   
				{    
					onItemDeleteComplete(response.responseText, status);   
				}  
			}); 
		}); 

function onItemDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divItemsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

// CLIENT-MODEL========================================================================= 
function validateItemForm() 
{  
	// CODE  
	if ($("#hospitalCode").val().trim() == "")  
	{   
		return "Insert Hospital Code.";  
	} 
 
	// NAME  
	if ($("#hospitalName").val().trim() == "")  
	{   
		return "Insert Hospital Name.";  
	} 
	// hospitalEmail  
	if ($("#hospitalEmail").val().trim() == "")  
	{   
		return "Insert Hospital Email.";  
	}
	
	// DESC  
	if ($("#hospitalDesc").val().trim() == "")  
	{   
		return "Insert Descprtion.";  
	} 
	
	// District  
	if ($("#hospitalDistrict").val().trim() == "")  
	{   
		return "Insert District.";  
	} 
	
	//PHONE-------------------------------  
	if ($("#hospitalTel").val().trim() == "")  
	{   
		return "Insert Phone Number.";  
	} 

	// is phone number numerical value  
	var tmpPrice = $("#hospitalTel").val().trim();  
	if (!$.isNumeric(tmpPrice))  
	{   
		return "Insert a numerical value for Phone Number.";  
	} 

	return true; 
}