<link rel="stylesheet" type="text/css" href="assets/DataTables/datatables.min.css"/>
<script type="text/javascript" src="assets/DataTables/datatables.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.cycle2.js"></script>
<script type="text/javascript" src="assets/js/jquery.cycle2.carousel.min.js"></script>
<script type="text/javascript" src="assets/js/jquery.cycle2.scrollVert.min.js"></script>
<link REL=STYLESHEET HREF="assets/css/default.css" TYPE="text/css"> 
<script type="text/javascript" src="assets/js/default.js"></script>
<script type="text/javascript">
	
$(document).ready( function () {
	$('#propertyTable tfoot th').each( function () {
		var title = $(this).text();
		$(this).html( '<input type="text" placeholder="Search '+title+'" />' );
	} );
	var table = $('#propertyTable').DataTable();
	table.columns().every( function () {
		var that = this;
		$( 'input', this.footer() ).on( 'keyup change', function () {
			if ( that.search() !== this.value ) {
				that.search( this.value )
					.draw();
			}
		} );
	} );
} );
</script>