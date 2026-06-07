// Task 1
try {
    console.log(squareDeclaration(5));
    console.log(squareExpres(5));
    console.log(squareArrow(5));
} catch (error) {

    console.log("error " + error);
}

function squareDeclaration(x) {
    return x * x;
}

const squareExpres = function (x) {
    return x * x;
}

const squareArrow = (x) => x * x;

console.log(squareDeclaration(5));
console.log(squareExpres(5));
console.log(squareArrow(5));

// Task 2
function makeLogger(prefix) {
    let count = 0;
    return function log(msg) {
        count++
        console.log("[" + prefix + " #" + count + "] " + msg);
    }
}
const warn = makeLogger("WARN");
warn("диск заповнений");
warn("мало пам'яті");

const info = makeLogger("INFO");
info("користувач зайшов");

// Task 3
const user = {
    name: "Оля",
    greet() {
        console.log("Привіт від " + this.name)
    },
    greetLater() {
        setTimeout(() => {
            console.log("Пізніше від " + this.name);
        }, 100);
    }
};

user.greet();
user.greetLater();

// Task 4
function once(fn) {
    let called = false;
    let result;
    return function (...args) {
        if (!called) {
            result = fn(...args);
            called = true;
        }
        return result;
    }
}

const initialize = once(() => {
    console.log("Ініціалізація...");
    return 42;
});

console.log(initialize());
console.log(initialize());
console.log(initialize());
console.log(initialize());
