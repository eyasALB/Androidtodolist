/**
 * Created by eyas on 19/08/2016.
 */


function Item(title, description)
{
    this.title = title;
    this.description = description;
}


//Creating a new XMLHttpRequest object
var xhr = new XMLHttpRequest(); 

var currentItemToUpdate = 0;
var todolistnamespace = {};
function updateCurrentItemToUpdate(id)
{
	var txt = id+"";
	var arr = txt.split("-");
	currentItemToUpdate = arr[1];

}

function deleteItem(id)
{   var txt = id+"";
    var arr = txt.split("-");
    var reqURL = 'rest/user/deleteitem?itemid='+arr[1];
	//Create a asynchronous POST request
	xhr.open("delete", reqURL, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4)
		{
			if( xhr.status == 200) 
			{
				$("#set"+arr[1]).remove();
			}
		}
	
	};
	 xhr.send(null);
    
}

function updateSelectedItemItem(Item)
{
	
	    var reqURL = 'rest/user/updateitem?title='+Item.title+"&description="+Item.description+"&itemid="+currentItemToUpdate;
		//Create a asynchronous POST request
		xhr.open("POST", reqURL, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4)
			{
				if( xhr.status == 200) 
				{
					getItems();
//					var jsItem = JSON.parse(xhr.responseText);
//					$("#set"+jsItem.id).remove();
//					//$("#set"+jsItem.id).html(null).trigger('create');
//					//$("#set"+jsItem.id).load(window.location.href + " #set" );
//					
//					var deleteButton = "<button type='button'  id='del-"+ jsItem.id + "'data-position='fixed' onclick='deleteItem(jQuery(this).attr(\"id\"))' data-icon='delete' data-theme='a' data-iconpos='right' data-mini='true' data-inline='true' >delete</button>";
//					var updateButtomn ="<a href='updateitem.html'  id='update-"+ jsItem.id + "' data-role='button' class='ui' data-transition='pop' data-theme='b' data-icon='gear' data-mini='true' data-rel='dialog' data-inline='true' onclick='return updateCurrentItemToUpdate(jQuery(this).attr(\"id\"));'>update</a>";
//					//var collapse = "<div data-role='collapsible' data-theme='b' data-content-theme='b' id='set"+ nextId + "'><h3>"+Item.title+" "+deleteButton+updateButtomn+"</h3><p>"+Item.description+".</p></div>";
//					var collapse = "<div data-role='collapsible' data-theme='b' data-content-theme='b' id='set"+ jsItem.id + "'><h3 id='title-set"+ jsItem.id + "'>"+jsItem.title+"</h3><p id='description-set"+ jsItem.id + "'>"+jsItem.description+".</p>"+deleteButton+updateButtomn+"</div>";
//					$("#set").append( collapse ).trigger('create');
					currentItemToUpdate = -1;
				}
			}
		
		};
		 xhr.send(null);

}

function getItems()
{
	var reqURL = 'rest/user/getitems';
	//Create a asynchronous POST request
	xhr.open("GET", reqURL, true);
   
	//When readyState is 4 then get the server output
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4)
		{
			if( xhr.status == 200) 
			{
				$("#set").html(null).trigger('create');
				var jsItemArray = JSON.parse(xhr.responseText);
				for(i = 0; i < jsItemArray.length; i++) {
					var deleteButton = "<button type='button'  id='del-"+ jsItemArray[i].id + "'data-position='fixed' onclick='deleteItem(jQuery(this).attr(\"id\"))' data-icon='delete' data-theme='a' data-iconpos='right' data-mini='true' data-inline='true' >delete</button>";
					var updateButtomn ="<a href='updateitem.html'  id='update-"+ jsItemArray[i].id + "' data-role='button' class='ui' data-transition='pop' data-theme='b' data-icon='gear' data-mini='true' data-rel='dialog' data-inline='true' onclick='return updateCurrentItemToUpdate(jQuery(this).attr(\"id\"));'>update</a>";
					//var collapse = "<div data-role='collapsible' data-theme='b' data-content-theme='b' id='set"+ nextId + "'><h3>"+Item.title+" "+deleteButton+updateButtomn+"</h3><p>"+Item.description+".</p></div>";
					var collapse = "<div data-role='collapsible' data-theme='b' data-content-theme='b' id='set"+ jsItemArray[i].id + "'><h3 id='title-set"+ jsItemArray[i].id + "'>"+jsItemArray[i].title+"</h3><p id='description-set"+ jsItemArray[i].id + "'>"+jsItemArray[i].description+".</p>"+deleteButton+updateButtomn+"</div>";
					$("#set").append( collapse ).trigger('create');
					//nextId++;
				}
			}
			else
			{
				alert('Something is wrong !!');
			}
		}
		
	};
	
	  xhr.send();

}

function addItemtolist(Item) {
	
	var reqURL = 'rest/user/addItem?title='+Item.title+"&description="+Item.description;
	//Create a asynchronous POST request
	xhr.open("POST", reqURL, true);
   
	//When readyState is 4 then get the server output
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4)
		{
			if( xhr.status == 200) 
			{
				var jsItem = JSON.parse(xhr.responseText);
				var deleteButton = "<button type='button'  id='del-"+ jsItem.id + "'data-position='fixed' onclick='deleteItem(jQuery(this).attr(\"id\"))' data-icon='delete' data-theme='a' data-iconpos='right' data-mini='true' data-inline='true' >delete</button>";
				var updateButtomn ="<a href='updateitem.html'  id='update-"+ jsItem.id + "' data-role='button' class='ui' data-transition='pop' data-theme='b' data-icon='gear' data-mini='true' data-rel='dialog' data-inline='true' onclick='return updateCurrentItemToUpdate(jQuery(this).attr(\"id\"));'>update</a>";
				var collapse = "<div data-role='collapsible' data-theme='b' data-content-theme='b' id='set"+ jsItem.id + "'><h3 id='title-set"+ jsItem.id + "'>"+jsItem.title+"</h3><p id='description-set"+ jsItem.id + "'>"+jsItem.description+".</p>"+deleteButton+updateButtomn+"</div>";
				$("#set").append( collapse ).trigger('create');
			
			}
			else
			{
				alert('Something is wrong !!');
			}
		}
		
	};
	  xhr.send(null);
}

function login(email,password)
{  
    var reqURL = 'rest/user/login?email='+email+"&password="+password;
	//Create a asynchronous POST request
	xhr.open("POST", reqURL, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4)
		{
			if( xhr.status == 200) 
			{
				$.mobile.changePage("/androidToDoListFinal/todolist.html");
			
			}
			else
			{
				$.mobile.changePage("/finalAndroidToDoList/error.html");
			}
		}
	
	};
	 xhr.send(null);
}
function register(name,password,email)
{  
    var reqURL = 'rest/user/register?name='+name+'&email='+email+'&password='+password;
	//Create a asynchronous POST request
	xhr.open("POST", reqURL, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4)
		{
			if( xhr.status == 200) 
			{
				
					$.mobile.changePage("/androidToDoListFinal/todolist.html");
			
			}
			else
			{
				$.mobile.changePage("finalAndroidToDoList/error.html");
			}
		}
	
	};
	 xhr.send(null);
}

function logout()
{  
    var reqURL = 'rest/user/logout';
	//Create a asynchronous POST request
	xhr.open("GET", reqURL, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4)
		{
			if( xhr.status == 200) 
			{
				$( "#set" ).load(window.location.href + " #set" );
				
					$.mobile.changePage("/androidToDoListFinal/home.html");
			
			}
			else
			{
				$.mobile.changePage("finalAndroidToDoList/error.html");
			}
		}
	
	};
	 xhr.send(null);
}

$(document).on("pageinit", function() {
    $("#expand").click(function () {
        $("#set").children(":last").trigger("expand");
    });
    $("#collapse").click(function () {
        $("#set").children(":last").trigger("collapse");
    });
$("#login-page").css({"background-color":"yellow"})
});