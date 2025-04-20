$(document).ready(function() {
  let currentPage = 1;
  let rowsPerPage = 5;
  let totalUsers = [];
  
  loadUsers();
  
  $('#search-button').click(function() {
    currentPage = 1;
    loadUsers();
  });
  
  $('#reset-button').click(function() {
    $('#search-input-id').val('');
    $('#search-input-email').val('');
    currentPage = 1;
    loadUsers();
  });
  
  function loadUsers() {
    const id = $('#search-input-id').val().trim();
    const email = $('#search-input-email').val().trim();
    
    let params = {};
    if (id) params.id = id;
    if (email) params.email = email;
    
    $('#users-table').html('<tr><td colspan="6">Loading...</td></tr>');
    
    $.ajax({
      url: 'getUsers',
      type: 'GET',
      data: params,
      dataType: 'json',
      success: function(response) {
        if (response.success) {
          totalUsers = response.users;
          displayUsers();
          updatePagination();
        } else {
          showNotification('Error: ' + response.message, 'error');
        }
      },
      error: function(xhr, status, error) {
        showNotification('Server error: ' + error, 'error');
      }
    });
  }
  
  function displayUsers() {
    let html = '';
    
    if (totalUsers.length === 0) {
      html = '<tr><td colspan="6">No users found</td></tr>';
      $('#users-table').html(html);
      return;
    }
    
    // Calculate the start and end indices for the current page
    const startIndex = (currentPage - 1) * rowsPerPage;
    const endIndex = Math.min(startIndex + rowsPerPage, totalUsers.length);
    
    // Get only the users for the current page
    const usersToDisplay = totalUsers.slice(startIndex, endIndex);
    
    usersToDisplay.forEach(function(user) {
      html += `
        <tr data-id="${user.userId}">
          <td>${user.userId}</td>
          <td>${user.name}</td>
          <td>${user.email}</td>
          <td>${user.phone || 'N/A'}</td>
          <td>${user.address || 'N/A'}</td>
          <td>$${(user.creditBalance || 0).toFixed(2)}</td>
        </tr>
      `;
    });
    
    $('#users-table').html(html);
  }
  
  function updatePagination() {
    const totalPages = Math.ceil(totalUsers.length / rowsPerPage);
    let paginationHtml = '';
    
    // Previous button
    paginationHtml += `<button class="pagination-btn prev-btn" ${currentPage === 1 ? 'disabled' : ''}>&laquo;</button>`;
    
    // Page numbers
    for (let i = 1; i <= totalPages; i++) {
      paginationHtml += `<button class="pagination-btn page-btn ${currentPage === i ? 'active' : ''}" data-page="${i}">${i}</button>`;
    }
    
    // Next button
    paginationHtml += `<button class="pagination-btn next-btn" ${currentPage === totalPages ? 'disabled' : ''}>&raquo;</button>`;
    
    $('#pagination').html(paginationHtml);
    
    // Add event listeners to pagination buttons
    $('.page-btn').click(function() {
      currentPage = parseInt($(this).data('page'));
      displayUsers();
      updatePagination();
    });
    
    $('.prev-btn').click(function() {
      if (currentPage > 1) {
        currentPage--;
        displayUsers();
        updatePagination();
      }
    });
    
    $('.next-btn').click(function() {
      if (currentPage < totalPages) {
        currentPage++;
        displayUsers();
        updatePagination();
      }
    });
  }
  
  function showNotification(message, type) {
    const notification = $('#notification');
    notification.removeClass('success error').addClass(type);
    notification.text(message);
    notification.show();
    
    setTimeout(function() {
      notification.hide();
    }, 5000);
  }
});