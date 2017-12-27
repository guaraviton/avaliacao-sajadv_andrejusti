var app = angular.module('app', ['app.controller', 'app.resources', 'app.services', 'ngRoute', 'ui.select','ngSanitize','ui.bootstrap','datatables','datatables.buttons','toaster','ngAnimate', 'fend.progressbar.loading','ui.mask', 'angular-jquery-mask','angularFileUpload','cgBusy','ngBusy', 'role-utils', 'ui.utils.masks', 'colorpicker.module', 'wysiwyg.module'])
.run(['uibDatepickerPopupConfig', function(datepickerPopupConfig) {
    datepickerPopupConfig.appendToBody = true;
    datepickerPopupConfig.closeText = "Fechar";
    datepickerPopupConfig.currentText = "Hoje";
    datepickerPopupConfig.clearText = "Limpar";	
    datepickerPopupConfig.datepickerPopup = "dd/MM/yyyy";
    datepickerPopupConfig.showButtonBar = false;
}])
.run(['uibDatepickerConfig', function(uibDatepickerConfig) {
	uibDatepickerConfig.showWeeks = false;
}])
.run(['DTDefaultOptions',function(DTDefaultOptions) {
    DTDefaultOptions.setLanguageSource('assets/libs/datatables/Portuguese-Brasil.json');
    DTDefaultOptions.setDisplayLength(10);
    DTDefaultOptions.setOption('info', true);
    DTDefaultOptions.setOption('lengthChange',false);
}])
.run(function($rootScope) {
	$rootScope.confirm = function(titulo, mensagem, botaoCancelar, botaoOk, callback) {
    	var confirmModal = 
    		$('<div class="modal fade">' +   
    			'<div class="vertical-alignment-helper">' +
    				'<div class="modal-sm modal-dialog vertical-align-center">' +
    					'<div class="modal-content">' +
    						'<div class="modal-header">' +
    							'<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>' +
    							'<h4 class="modal-title" id="modalLabel">' + titulo +'</h4> '+
    						'</div>' +
    						'<div class="modal-body">' +
    							mensagem +
    						'</div>' +
    						'<div class="modal-footer">' +
    							'<button type="button" class="btn btn-default" data-dismiss="modal">'+botaoCancelar+'</button>' +
    							'<button type="button" id="okButton" class="btn btn-primary">'+botaoOk+'</button>' +
    						'</div>' +
    					'</div>' +
    				'</div>' +
    			'</div>'+
			'</div>');
		confirmModal.find('#okButton').click(function(event) {
			event.preventDefault();
			callback();
			confirmModal.modal('hide');
		}); 
	    confirmModal.modal('show');    
	}; 
})
.constant('APP_CONFIG', {
		'TOKEN_TIME_REFRESH': (25 * 60 * 1000),
		'TOKEN_HEADER_NAME': 'Authorization',
        'ORDENS_SERVICO_ATIVAS_TIME_REFRESH': (5 * 60 * 1000)
	}
)
.constant('LISTA_ETAPAS_ORDEM_SERVICO',
	[
		{id: 1, nome: 'Aberta'},
		{id: 2, nome: 'Análise Orcamento'},
		{id: 3, nome: 'Pendente de aprovação do Orçamento pelo Cliente'},
		{id: 4, nome: 'Orcamento Reprovado Cliente'},
		{id: 5, nome: 'Orcamento Aprovado Cliente'},
		{id: 6, nome: 'Executando Serviço'},
		{id: 7, nome: 'Serviço Finalizado'},
		{id: 8, nome: 'Aguardando Retirada'},
		{id: 9, nome: 'Finalizada'},
		{id: 10, nome: 'Cancelada'}
	]
);

var appController = angular.module('app.controller', []);
var appResource = angular.module('app.resources', ['ngResource']);
var appServices = angular.module('app.services', []);