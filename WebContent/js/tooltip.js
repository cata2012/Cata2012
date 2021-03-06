$(document).ready(function() {
	var i = 0;
	while(true) {
		var mistake = "#mistake" + i;
		var content = "#tooltipcontent" + i;
		if($(mistake).length > 0) {
			showToolTip(mistake, content);
		}
		else {
			break;
		}
		i = i + 1;
	}
});

function showToolTip(mistake, content) {
	$(mistake).qtip({
		content: $(content).html(),
		show: 'mouseover',
		hide: {
	          fixed: true,
	          delay: 200
	    },
		position: {
    		corner: {
    	    	target: 'bottomMiddle',
    	        tooltip: 'topMiddle'
    	    }
		},
		style: {
			border: {
                 width: 5,
                 radius: 10,
                 color:'#999999'
            },
            width: 420,
            padding: 8,
			tip: true,
        	name: 'light'
		}
	});
}