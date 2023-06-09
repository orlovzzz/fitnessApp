function showAccountModal() {
    var modal = document.getElementById("accountModal");
    var overlay = document.getElementById("overlay");

    modal.style.display = "block";
    overlay.style.display = "block";
}

        function hideAccountModal() {
          var modal = document.getElementById("accountModal");
          var overlay = document.getElementById("overlay");

          modal.style.display = "none";
          overlay.style.display = "none";
        }

        function showBalanceModal() {
          var modal = document.getElementById("balanceModal");

          modal.style.display = "block";
        }

        function showOrderModal() {
          var modal = document.getElementById("orderModal");
          var overlay = document.getElementById("overlay");

          modal.style.display = "block";
          overlay.style.display = "block";
        }

        function hideOrderModal() {
          var modal = document.getElementById("orderModal");
          var overlay = document.getElementById("overlay");

          modal.style.display = "none";
          overlay.style.display = "none";
        }