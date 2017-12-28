//Init Tooltip
$(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
//Ajax Requet to servlet   
$(document).ready(function(){
			 $("form").on("submit",function(event){
				 $.post("URL",$(this).serialize(),function(data,status,xhr){
				 }).fail(function(xhr, status, error) {
				    });
			     
				 event.preventDefault();
			 });
		 });
//Copy text to clipboard     
function copyToClipboard(clickedElement)
{
    var $temp = $("<input>");
    $("body").append($temp);
    $temp.val($(clickedElement).text().trim()).select();
    document.execCommand("copy");
    $temp.remove();
}