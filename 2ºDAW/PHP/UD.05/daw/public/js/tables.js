/**
 * Script para manejo de tablas
 * Funcionalidades para tablas: ordenamiento, búsqueda, paginación
 * @package JavaScript
 * @category Tables
 * @author Benjamín Santiago González
 * @since 2025
 */

$(document).ready(function () {
  // Inicializar ordenamiento de tablas
  initTableSorting();

  // Inicializar búsqueda en tablas
  initTableSearch();

  // Inicializar paginación
  initTablePagination();

  // Inicializar acciones de tabla
  initTableActions();
});

/**
 * Inicializa el ordenamiento de columnas en tablas
 */
function initTableSorting() {
  $('.table th.sortable').on('click', function () {
    var table = $(this).closest('table');
    var header = $(this);
    var columnIndex = header.index();
    var isAsc = header.hasClass('asc');

    // Limpiar clases de ordenamiento
    table.find('th.sortable').removeClass('asc desc');

    // Alternar orden
    if (isAsc) {
      header.addClass('desc');
      sortTable(table, columnIndex, 'desc');
    } else {
      header.addClass('asc');
      sortTable(table, columnIndex, 'asc');
    }
  });
}

/**
 * Ordena una tabla por una columna específica
 * @param {jQuery} table - Tabla a ordenar
 * @param {number} columnIndex - Índice de la columna
 * @param {string} direction - Dirección (asc/desc)
 */
function sortTable(table, columnIndex, direction) {
  var tbody = table.find('tbody');
  var rows = tbody.find('tr').toArray();

  rows.sort(function (a, b) {
    var aValue = $(a).find('td').eq(columnIndex).text().trim();
    var bValue = $(b).find('td').eq(columnIndex).text().trim();

    // Intentar convertir a número si es posible
    var aNum = parseFloat(aValue.replace(/[^0-9.-]+/g, ""));
    var bNum = parseFloat(bValue.replace(/[^0-9.-]+/g, ""));

    if (!isNaN(aNum) && !isNaN(bNum)) {
      aValue = aNum;
      bValue = bNum;
    }

    if (direction === 'asc') {
      return aValue > bValue ? 1 : -1;
    } else {
      return aValue < bValue ? 1 : -1;
    }
  });

  // Reordenar filas
  tbody.empty().append(rows);

  // Añadir animación
  tbody.find('tr').addClass('fade-in');
  setTimeout(function () {
    tbody.find('tr').removeClass('fade-in');
  }, 300);
}

/**
 * Inicializa la búsqueda en tablas
 */
function initTableSearch() {
  $('.table-search').on('keyup', function () {
    var searchTerm = $(this).val().toLowerCase();
    var table = $(this).data('table');
    var tableBody = $('#' + table + ' tbody');

    tableBody.find('tr').each(function () {
      var rowText = $(this).text().toLowerCase();
      if (rowText.indexOf(searchTerm) > -1) {
        $(this).show();
      } else {
        $(this).hide();
      }
    });

    // Mostrar mensaje si no hay resultados
    var visibleRows = tableBody.find('tr:visible').length;
    if (visibleRows === 0 && searchTerm) {
      showNoResultsMessage(tableBody);
    } else {
      hideNoResultsMessage(tableBody);
    }
  });
}

/**
 * Muestra mensaje de no resultados
 * @param {jQuery} tableBody - Cuerpo de la tabla
 */
function showNoResultsMessage(tableBody) {
  var table = tableBody.closest('table');
  var messageId = table.attr('id') + '-no-results';

  if (!$('#' + messageId).length) {
    var message = $('<tr id="' + messageId + '"><td colspan="' + table.find('th').length + '" class="table-empty"><i class="fas fa-search"></i><p>No se encontraron resultados</p></td></tr>');
    tableBody.append(message);
  }
}

/**
 * Oculta mensaje de no resultados
 * @param {jQuery} tableBody - Cuerpo de la tabla
 */
function hideNoResultsMessage(tableBody) {
  var table = tableBody.closest('table');
  var messageId = table.attr('id') + '-no-results';
  $('#' + messageId).remove();
}

/**
 * Inicializa la paginación de tablas
 */
function initTablePagination() {
  $('.table-paginate').each(function () {
    var table = $(this);
    var rowsPerPage = parseInt(table.data('rows-per-page') || 10);
    var currentPage = 1;

    // Calcular número total de páginas
    var totalRows = table.find('tbody tr').length;
    var totalPages = Math.ceil(totalRows / rowsPerPage);

    // Crear controles de paginación si no existen
    var paginationId = table.attr('id') + '-pagination';
    if (!$('#' + paginationId).length) {
      var paginationHtml = `
                <div class="table-pagination" id="${paginationId}">
                    <div class="table-info">
                        Mostrando <span class="current-range">1-${Math.min(rowsPerPage, totalRows)}</span> de ${totalRows}
                    </div>
                    <nav>
                        <ul class="pagination pagination-sm mb-0">
                            <li class="page-item prev"><a class="page-link" href="#"><i class="fas fa-chevron-left"></i></a></li>
                            <li class="page-item next"><a class="page-link" href="#"><i class="fas fa-chevron-right"></i></a></li>
                        </ul>
                    </nav>
                </div>
            `;
      table.after(paginationHtml);
    }

    // Mostrar primera página
    showPage(table, 1, rowsPerPage);
    updatePaginationControls(paginationId, currentPage, totalPages, rowsPerPage, totalRows);

    // Manejar clics en botones de paginación
    $('#' + paginationId + ' .prev').on('click', function (e) {
      e.preventDefault();
      if (currentPage > 1) {
        currentPage--;
        showPage(table, currentPage, rowsPerPage);
        updatePaginationControls(paginationId, currentPage, totalPages, rowsPerPage, totalRows);
      }
    });

    $('#' + paginationId + ' .next').on('click', function (e) {
      e.preventDefault();
      if (currentPage < totalPages) {
        currentPage++;
        showPage(table, currentPage, rowsPerPage);
        updatePaginationControls(paginationId, currentPage, totalPages, rowsPerPage, totalRows);
      }
    });
  });
}

/**
 * Muestra una página específica de la tabla
 * @param {jQuery} table - Tabla
 * @param {number} page - Número de página
 * @param {number} rowsPerPage - Filas por página
 */
function showPage(table, page, rowsPerPage) {
  var start = (page - 1) * rowsPerPage;
  var end = start + rowsPerPage;

  table.find('tbody tr').each(function (index) {
    if (index >= start && index < end) {
      $(this).show();
    } else {
      $(this).hide();
    }
  });
}

/**
 * Actualiza los controles de paginación
 */
function updatePaginationControls(paginationId, currentPage, totalPages, rowsPerPage, totalRows) {
  var start = (currentPage - 1) * rowsPerPage + 1;
  var end = Math.min(currentPage * rowsPerPage, totalRows);

  $('#' + paginationId + ' .current-range').text(start + '-' + end);

  // Habilitar/deshabilitar botones
  $('#' + paginationId + ' .prev').toggleClass('disabled', currentPage === 1);
  $('#' + paginationId + ' .next').toggleClass('disabled', currentPage === totalPages);
}

/**
 * Inicializa acciones de tabla (editar, eliminar, ver)
 */
function initTableActions() {
  // Confirmación para eliminar
  $(document).on('click', '.btn-eliminar-tabla', function (e) {
    e.preventDefault();

    var itemName = $(this).data('item-name') || 'este elemento';
    var itemId = $(this).data('item-id') || '';
    var deleteUrl = $(this).attr('href');

    if (confirm(`¿Está seguro de eliminar ${itemName}? Esta acción no se puede deshacer.`)) {
      // Realizar solicitud AJAX para eliminar
      $.ajax({
        url: deleteUrl,
        method: 'DELETE',
        dataType: 'json',
        success: function (response) {
          if (response.success) {
            showAlert(response.message || 'Elemento eliminado correctamente', 'success');
            // Eliminar fila de la tabla
            $('tr[data-id="' + itemId + '"]').fadeOut(300, function () {
              $(this).remove();
            });
          } else {
            showAlert(response.message || 'Error al eliminar el elemento', 'danger');
          }
        },
        error: function () {
          showAlert('Error de conexión', 'danger');
        }
      });
    }
  });

  // Exportar tabla a CSV
  $('.btn-export-csv').on('click', function () {
    var table = $(this).data('table');
    exportTableToCSV(table);
  });

  // Imprimir tabla
  $('.btn-print-table').on('click', function () {
    var table = $(this).data('table');
    printTable(table);
  });
}

/**
 * Exporta una tabla a CSV
 * @param {string} tableId - ID de la tabla
 */
function exportTableToCSV(tableId) {
  var table = $('#' + tableId);
  var headers = [];
  var rows = [];

  // Obtener encabezados
  table.find('thead th').each(function () {
    headers.push($(this).text().trim());
  });

  // Obtener filas
  table.find('tbody tr').each(function () {
    var row = [];
    $(this).find('td').each(function () {
      row.push($(this).text().trim());
    });
    rows.push(row);
  });

  // Convertir a CSV
  var csvContent = "data:text/csv;charset=utf-8,";
  csvContent += headers.join(",") + "\n";
  rows.forEach(function (row) {
    csvContent += row.join(",") + "\n";
  });

  // Descargar
  var encodedUri = encodeURI(csvContent);
  var link = document.createElement("a");
  link.setAttribute("href", encodedUri);
  link.setAttribute("download", tableId + "_" + new Date().toISOString().slice(0, 10) + ".csv");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

/**
 * Imprime una tabla
 * @param {string} tableId - ID de la tabla
 */
function printTable(tableId) {
  var printWindow = window.open('', '_blank');
  var table = $('#' + tableId).clone();

  // Limpiar estilos y acciones
  table.find('.btn, .actions').remove();

  printWindow.document.write(`
        <html>
            <head>
                <title>Imprimir ${tableId}</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 20px; }
                    table { width: 100%; border-collapse: collapse; }
                    th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
                    th { background-color: #f2f2f2; }
                    @media print {
                        @page { margin: 0.5in; }
                    }
                </style>
            </head>
            <body>
                <h2>${document.title}</h2>
                <p>Fecha: ${new Date().toLocaleDateString('es-ES')}</p>
                ${table.prop('outerHTML')}
            </body>
        </html>
    `);

  printWindow.document.close();
  printWindow.focus();
  printWindow.print();
  printWindow.close();
}