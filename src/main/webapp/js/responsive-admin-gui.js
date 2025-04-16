// // Add this to a file named responsive-admin.js or include it in your existing JS files

// document.addEventListener('DOMContentLoaded', function() {
//     // Create and append the sidebar toggle button
//     const sidebar = document.querySelector('.sidebar');
//     const content = document.querySelector('.content');
    
//     const toggleButton = document.createElement('button');
//     toggleButton.className = 'sidebar-toggle';
//     toggleButton.innerHTML = '<i class="fas fa-bars"></i>';
//     document.body.appendChild(toggleButton);
    
//     // Toggle sidebar on mobile
//     toggleButton.addEventListener('click', function() {
//       sidebar.classList.toggle('active');
//     });
    
//     // Close sidebar when clicking outside of it on mobile
//     document.addEventListener('click', function(event) {
//       const isClickInsideSidebar = sidebar.contains(event.target);
//       const isClickOnToggleButton = toggleButton.contains(event.target);
      
//       if (!isClickInsideSidebar && !isClickOnToggleButton && window.innerWidth <= 768) {
//         sidebar.classList.remove('active');
//       }
//     });
    
//     // Handle responsive tables
//     function setupResponsiveTables() {
//       if (window.innerWidth <= 576) {
//         const tables = document.querySelectorAll('table');
//         tables.forEach(table => {
//           // Ensure all cells in these tables have appropriate aria labels for accessibility
//           const headers = Array.from(table.querySelectorAll('thead th')).map(th => th.textContent);
          
//           table.querySelectorAll('tbody tr').forEach(row => {
//             Array.from(row.querySelectorAll('td')).forEach((cell, index) => {
//               if (headers[index]) {
//                 cell.setAttribute('aria-label', headers[index]);
//               }
//             });
//           });
//         });
//       }
//     }
    
//     // Run on load and resize
//     setupResponsiveTables();
//     window.addEventListener('resize', setupResponsiveTables);
//   });
document.addEventListener('DOMContentLoaded', function() {
  console.log('Responsive admin JS loaded!');

  // Create and append the sidebar toggle button if it doesn't exist
  let toggleButton = document.querySelector('.sidebar-toggle');
  if (!toggleButton) {
      toggleButton = document.createElement('button');
      toggleButton.className = 'sidebar-toggle';
      toggleButton.innerHTML = '<i class="fas fa-bars"></i>';
      document.body.appendChild(toggleButton);
  }
  
  const sidebar = document.querySelector('.sidebar');
  
  // Make sure we have both elements before setting up listeners
  if (!sidebar || !toggleButton) {
      console.error('Sidebar or toggle button not found!');
      return;
  }
  
  // Toggle sidebar on mobile
  toggleButton.addEventListener('click', function(event) {
      event.preventDefault();
      event.stopPropagation();
      sidebar.classList.toggle('active');
      console.log('Sidebar toggled:', sidebar.classList.contains('active'));
  });
  
  // Close sidebar when clicking outside of it on mobile
  document.addEventListener('click', function(event) {
      if (!sidebar) return;
      
      const isClickInsideSidebar = sidebar.contains(event.target);
      const isClickOnToggleButton = toggleButton.contains(event.target);
      
      if (!isClickInsideSidebar && !isClickOnToggleButton && window.innerWidth <= 768) {
          sidebar.classList.remove('active');
      }
  });
  
  // Handle responsive tables
  function setupResponsiveTables() {
      const tables = document.querySelectorAll('table');
      tables.forEach(table => {
          if (!table.querySelector('thead')) return;
          
          // Get headers
          const headers = Array.from(table.querySelectorAll('thead th')).map(th => th.textContent.trim());
          
          table.querySelectorAll('tbody tr').forEach(row => {
              Array.from(row.querySelectorAll('td')).forEach((cell, index) => {
                  if (headers[index]) {
                      cell.setAttribute('aria-label', headers[index]);
                  }
              });
          });
      });
  }
  
  // Run on load and resize
  setupResponsiveTables();
  window.addEventListener('resize', setupResponsiveTables);
});