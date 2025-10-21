// Custom date format: "DD/MM/YYYY - HH:MM:SS"
const formatDate = () => {
  // Get the current date and time
  const now = new Date();

  // Extract day, month, year, hours, minutes, and seconds
  const day = String(now.getDate()).padStart(2, '0');
  const month = String(now.getMonth() + 1).padStart(2, '0'); // Months are zero-indexed
  const year = now.getFullYear();

  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');

  // Format the date and time
  return `${day}/${month}/${year} - ${hours}:${minutes}:${seconds}`;
};

console.log(formatDate());