/**
 * @author dailey.dai@openthinks.com
 * @date 2019/03/15
 */
var seq_cols = [
	{
		name: 'prj_short_name',
		label: '项目缩写',
		width: 0.3
	},
	{
		name: 'next',
		label: '流水号',
		width: 200,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
			}
		}
	},
	{
		name: 'min',
		label: '最小值',
		width: 200,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
			}
		}
	},
	{
		name: 'max',
		label: '最大值',
		width: 200,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				return dataValue == null || dataValue == 'null' ? '' : dataValue;
			}
		}
	},
	{
		name: 'continus',
		label: '递增',
		html: true,
		width: 80,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				var val= ( dataValue == null || dataValue == 'null' ? 'true' : dataValue);
				var html_yes= "<i class='icon icon-check'></i>",html_no="<i class='icon icon-times'></i>";
				if(val==true || val=="true"){
					return html_yes;
				}else{
					return html_no;
				}
			}
		}
	},
	{
		name: 'option',
		label: '操作',
		width: 80,
		sort: false,
		html: true,
		valueOperator: {
			getter: function(dataValue, cell, dataGrid) {
				var row = dataGrid.getDataItem(cell.rowIndex - 1),
				v_id = row['id'],
				editUrl = getBaseUrl('seq/edit/' + v_id),
				modContent = ' <a  href="' + editUrl + '" title="更新"><i class="icon icon-pencil"></i></a>';
				return modContent;
			}
		}
	}
];
///////////////////////////////////////
(function(){
$('#datagridLog').datagrid({
	height:'page',
	dataSource: {
		cols: seq_cols,
		remote: function(params, datagrid) {
			var urlBase = getBaseUrl('api/seq/search');
			return {
				url: urlBase,
				// 请求类型
				type: 'GET',
				// 数据类型
				dataType: 'json'
			};
		}
	},
	states: {
		pager: {
			page: 1,
			recPerPage: 15
		},
		search: $('#inputSearchLog').val()
	},
	//			   hoverCell:true,
	sortable: true,
	autoOnSearchChange:false,
	afterLoad:function(){
		$('[data-toggle="tooltip"]').tooltip();
	}
});

$("#searchButton").unbind("click").bind("click",function(){
	var myDataGrid = $('#datagridLog').data('zui.datagrid');
	myDataGrid.search($('#inputSearchLog').val());
})


})();

