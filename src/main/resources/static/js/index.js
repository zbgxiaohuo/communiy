$(function(){
	$("#publishBtn").click(publish);
});

/*发布（视觉 效果上的）*/
function publish() {
	$("#publishModal").modal("hide");
	$("#hintModal").modal("show");
	setTimeout(function(){
		$("#hintModal").modal("hide");
	}, 2000);
}