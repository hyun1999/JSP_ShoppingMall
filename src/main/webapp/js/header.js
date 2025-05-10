document.addEventListener("DOMContentLoaded", () => {
    const arrow = document.getElementById("arrow-icon");
    const dropdown = document.getElementById("dropdown-menu");
    const selectWrapper = document.querySelector(".select-wrapper");
    const selectedDisplay = document.getElementById("selected-category");
    const hiddenInput = document.getElementById("category-input");

    let isDropdownOpen = false;

    if (!arrow || !dropdown || !selectWrapper || !selectedDisplay || !hiddenInput) return;

    // 드롭다운 열기/닫기
    selectWrapper.addEventListener("click", (e) => {
        isDropdownOpen = !isDropdownOpen;
        dropdown.style.display = isDropdownOpen ? "block" : "none";
        arrow.src = `${contextPath}/images/${isDropdownOpen ? "up-arrow" : "down-arrow"}.png`;
    });

    // 항목 클릭 시 선택 처리
    dropdown.addEventListener("click", (e) => {
        const target = e.target.closest(".dropdown-item");
        if (!target) return;

        const value = target.dataset.value;
        const text = target.textContent;

        selectedDisplay.textContent = text;
        hiddenInput.value = value;

        isDropdownOpen = false;
        dropdown.style.display = "none";
        arrow.src = `${contextPath}/images/down-arrow.png`;
    });

    // 외부 클릭 시 닫기
    document.addEventListener("click", (e) => {
        if (!selectWrapper.contains(e.target)) {
            isDropdownOpen = false;
            dropdown.style.display = "none";
            arrow.src = `${contextPath}/images/down-arrow.png`;
        }
    });
});
