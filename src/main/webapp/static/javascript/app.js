window.onbeforeunload = function() {
	setNextPage(2);
};

$("#checkAll").on("click", function() {
	var checkedValue = this["checked"];
	$("input[name='id']").prop("checked", checkedValue);
});

$("#clearSearchInput").on("click", function() {
	$("#searchInput").val("");
});

$("#loadMoreProducts").on("click", function() {
	toggleHidden();
	var page = parseInt(getNextPage());
	$.ajax({
		type: "GET",
		url: ctx + "/dashboard",
		data: "action=LoadMore&query=" + query + "&page=" + page,
		success: function(data) {
			$("tbody").append(data);
			if (maxPageNumber == page) {
				$("#loadMoreProducts").addClass("hidden");
			} else {
				setNextPage(page + 1);
			}
		}
	});
	toggleHidden();
});

$("#deleteProducts").on("click", function() {
	var ids = $("input[name='id']:checked").map(function() {
		return "id=" + this["value"]
	}).get().join("&");
	$.ajax({
		type: "DELETE",
		url: ctx + "/dashboard?" + ids,
		success: function() {
			$("tr").has(":checkbox:checked").remove();
			var tableDataRowsCount = $("tr > td").length;
			if (tableDataRowsCount == 0) {
				window.location.reload();
			}
		}
	})
});

function toggleHidden() {
	$("#loadMoreProducts").toggleClass("hidden");
	$("#spinner").toggleClass("hidden");
}

function getNextPage() {
	return $("#page").val();
}

function setNextPage(page) {
	$("#page").val(page);
}

function verifyIfMoreThan1Checked(event) {
	var countChecked = $("input[name='id']:checked").length;
	if (countChecked > 1) {
		event.preventDefault();
		alert('Please, select only one row that should be edited!');
	}
}
