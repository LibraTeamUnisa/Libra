//Init Tooltip
$(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
//Ajax Requet to servlet   
$(document).ready(function(){
			 $("form").on("submit",function(event){
				 $.post("periodoReport",$(this).serialize(),function(data,status,xhr){
					 toastr.success(data,'Ottimo!');
				 }).fail(function(xhr, status, error) {
					 toastr.error(data,'Accipicchia!');
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