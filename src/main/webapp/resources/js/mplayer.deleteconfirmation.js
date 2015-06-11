$(document).ready(function() {
	$('a[data-confirm]').click(function(ev) {
		var href = $(this).attr('href');
		if (!$('#dataConfirmModal').length) {
			$('body').append('<div id="dataConfirmModal" class="modal"><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><a href="" data-dismiss="modal" aria-hidden="true" class="close">x</a><h2>Delete media</h2></div><div class="modal-body"></div><div class="modal-footer"><a href="" data-dismiss="modal" aria-hidden="true" class="btn">Cancel</a><a id="dataConfirmOK" class="btn confirm btn-danger">Confirm</a></div></div></div></div>');
		} 
		$('#dataConfirmModal').find('.modal-body').text($(this).attr('data-confirm'));
		$('#dataConfirmOK').attr('href', href);
		$('#dataConfirmModal').modal({show:true});
		return false;
	});
});