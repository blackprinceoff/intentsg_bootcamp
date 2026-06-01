// Task 1
const bankAccount = {
    owner: "Myroslav",
    balance: 1000,
    deposit: function (amount) {
        this.balance += amount;
    },
    withdraw: function (amount) {
        if (amount > this.balance) {
            console.log("Error, not enough money");
            return;
        } else {
            this.balance -= amount;
        }
    },
    getBalance: function () {
        return this.balance;
    },
    formatHistory: function () {
        return `Owner: ${this.owner}, Balance: ${this.balance}`;
    }
};

console.log(bankAccount);
bankAccount.deposit(300);
console.log(bankAccount.balance);
bankAccount.withdraw(50);
console.log(bankAccount.balance);
bankAccount.withdraw(1500);
console.log(bankAccount.balance);
console.log(bankAccount.getBalance());
console.log(bankAccount.formatHistory());

// Task 2
const prices = { apple: 25, banana: 12, mango: 60, grape: 45 };
console.log(prices);
console.log(Object.keys(prices));
function discount(obj, percent) {
    const result = {};
    const keys = Object.keys(obj);
    for (let i = 0; i < keys.length; i++) {
        const key = keys[i];
        const newPrice = (obj[key] - obj[key] * percent / 100).toFixed(2);
        result[key.toUpperCase()] = newPrice;
    }
    return result;
}

const discounted = discount(prices, 10);
console.log("Discounted prices:", discounted);
console.log("Original prices (not changed):", prices);
console.log("Items cheaper than 30:");
const discountedKeys = Object.keys(discounted);
for (let i = 0; i < discountedKeys.length; i++) {
    const key = discountedKeys[i];
    if (Number(discounted[key]) < 30) {
        console.log(`${key}: ${discounted[key]}`);
    }
}

// Task 3
const students = [
    { name: "Alice", grades: { math: 90, english: 85 } },
    { name: "Bob", grades: { math: 70, english: 95 } }
];

function report(students) {
    const result = [];
    for (let i = 0; i < students.length; i++) {
        const subjects = Object.keys(students[i].grades);
        let sum = 0;
        for (let j = 0; j < subjects.length; j++) {
            sum += students[i].grades[subjects[j]];
        }
        const average = (sum / subjects.length).toFixed(1);

        let best = subjects[0];
        for (let j = 1; j < subjects.length; j++) {
            if (students[i].grades[subjects[j]] > students[i].grades[best]) {
                best = subjects[j];
            }
        }

        result.push({ name: students[i].name, average: average, best: best });
    }
    return result;
}

console.log("Student report:", report(students));
console.log("Original students (not changed):", students);

// Task 4
const sentence = "JavaScript is a very popular programming language";

const words = sentence.split(' ');
console.log("Words:", words);

const filtered = words.filter(function (word) {
    return word !== "very";
});
console.log("Without 'very':", filtered);

const capitalized = filtered.map(function (word) {
    return word[0].toUpperCase() + word.slice(1);
});
console.log("Capitalized:", capitalized);

const result = capitalized.join(' ');
console.log("Final sentence:", result);
