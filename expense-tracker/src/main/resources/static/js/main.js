console.log("main.js loaded ✅");

document.getElementById("expenseForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const editingId = document.getElementById("editingId").value;

    const expense = {
        description: document.getElementById("description").value.trim(),
        amount: parseFloat(document.getElementById("amount").value),
        category: document.getElementById("category").value.trim(),
        date: document.getElementById("date").value
    };

    const url = editingId
        ? `/api/updateExpense/${editingId}`
        : "/api/addExpense";

    const method = editingId ? "PUT" : "POST";

    try {
        const res = await fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(expense)
        });

        if (res.ok) {
            alert(editingId ? "Expense Updated" : "Expense Added");
            document.getElementById("expenseForm").reset();
            document.getElementById("editingId").value = "";
            document.getElementById("submitButton").innerText = "Add Expense";
            document.getElementById("formTitle").innerText = "Add New Expense";
            await fetchExpenses(); // refresh
        } else {
            const errorText = await res.text();
            alert("Failed to " + (editingId ? "update" : "add") + " expense: " + errorText);
        }
    } catch (err) {
        console.error("Request failed:", err);
        alert("Something went wrong. Try again.");
    }
});

let allExpenses = [];

async function fetchExpenses() {
    try {
        const res = await fetch("/api/getExpenses");
        if (!res.ok) throw new Error("Failed to fetch expenses");

        allExpenses = await res.json();
        applyFilter(); // Filtered display
        console.log("Expenses loaded:", allExpenses);
    } catch (err) {
        console.error("Error in fetchExpenses():", err);
        alert("Could not load expenses.");
    }
}

function renderExpenses(expenses) {
    const list = document.getElementById("expenseList");
    list.innerHTML = "";

    if (!expenses || expenses.length === 0) {
        list.innerHTML = "<li>No expenses found</li>";
        return;
    }

    expenses.forEach(expense => {
        const li = document.createElement("li");
        li.classList.add("expense-item");
        li.innerHTML = `
            <b>${expense.description}</b> - ${expense.amount}$ - ${expense.category} - ${expense.date}
            <button class="delete-btn" onclick="deleteExpense(${expense.id})">Delete</button>
            <button class="edit-btn" onclick="editExpense(${expense.id})">Update</button>
        `;
        list.appendChild(li);
    });
}

function applyFilter() {
    const selectedCategory = document.getElementById("categoryFilter").value;
    if (selectedCategory === "all") {
        renderExpenses(allExpenses);
    } else {
        const filtered = allExpenses.filter(e => e.category.toLowerCase() === selectedCategory.toLowerCase());
        renderExpenses(filtered);
    }
}

async function deleteExpense(id) {
    if (!confirm("Delete this expense?")) return;

    try {
        const res = await fetch(`/api/deleteExpense/${id}`, { method: "DELETE" });
        if (res.ok) {
            alert("Deleted");
            await fetchExpenses();
        } else {
            alert("Delete failed");
        }
    } catch (err) {
        console.error("Delete error:", err);
    }
}

async function editExpense(id) {
    try {
        const selected = allExpenses.find(e => e.id === id);
        if (selected) {
            document.getElementById("description").value = selected.description;
            document.getElementById("amount").value = selected.amount;
            document.getElementById("category").value = selected.category;
            document.getElementById("date").value = selected.date;
            document.getElementById("editingId").value = selected.id;
            document.getElementById("submitButton").innerText = "Update Expense";
            document.getElementById("formTitle").innerText = "Edit Expense";
        }
    } catch (err) {
        console.error("Edit error:", err);
    }
}

async function getSummary() {
    try {
        const res = await fetch("/api/viewSummary");
        const text = await res.text();
        document.getElementById("summaryAmount").innerText = "Total: " + text;
    } catch (err) {
        console.error("Summary error:", err);
    }
}

window.onload = function () {
    fetchExpenses();
    getSummary();
};
