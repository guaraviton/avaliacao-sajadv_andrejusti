// Diretiva para contornar o problema que acontece quando o IOS exibe um teclado virtual na tela
// e altera temporariamente e de forma automatica as regras de estilo CSS com o valor de position
// fixed para absolute. Isso faz com que a barra de progresso do ngProgress se desloque para o
// meio da tela em alguns casos de forma permanente apos a utilizacao do teclado virtual.
// Esta diretiva atua em todos os elementos select, input e textarea dentro do elemento marcado
// com a diretiva, monitorando os eventos focus e blur destes elementos para alterar proativamente
// o position do elemento ngProgress-container para absolute enquanto um desses campos estiver com
// o foco, e alterar de volta para o valor original (absolute) quando o campo perder o foco.
angular.module('fend.progressbar.loading')
  .directive('ngProgressIosPositionFix', function() {
    return {
      restrict: 'A',
      link: function (scope, element) {
        // somente para iPhone, iPad e iPod
        if (navigator.userAgent.match(/iPhone|iPad|iPod/i)) {
          //No caso do ios a barra de progresso sempre fica no topo da tela pois a posicao fixed nao funcionava no login
          //$(element).on('focus', 'select, input, textarea', function () {
            // move o progress bar para o inicio da pagina, podendo ficar escondido caso o usuario
            // tenha feito scroll para baixo na pagina
            $('#ngProgress-container').css('position', 'absolute');
         // });
//          $(element).on('blur', 'select, input, textarea', function () {
//            // volta o progress bar para o topo da tela
//            $('#ngProgress-container').css('position', '');
//          });
        }
      }
    };
  });
