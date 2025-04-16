$(document).ready(function() {
    loadUsers();
    
    $('#search-button').click(function() {
      loadUsers();
    });
    
    $('#reset-button').click(function() {
      $('#search-input-id').val('');
      $('#search-input-email').val('');
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
            displayUsers(response.users);
          } else {
            showNotification('Error: ' + response.message, 'error');
          }
        },
        error: function(xhr, status, error) {
          showNotification('Server error: ' + error, 'error');
        }
      });
    }
    
    function displayUsers(users) {
      let html = '';
      
      if (users.length === 0) {
        html = '<tr><td colspan="6">No users found</td></tr>';
      } else {
        users.forEach(function(user) {
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
      }
      
      $('#users-table').html(html);
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
