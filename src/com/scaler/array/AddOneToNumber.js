function AddOneToNumber(A) {
    let N = A.length

    let carry = 1;
    for(let i=N-1; i>=0; i--) {
        let sumOfDigit = A[i] + carry;
        A[i] = sumOfDigit % 10;
        carry = Math.floor(sumOfDigit / 10);
        if (carry === 0) break;
    }
    // Add carry to the front of A
    if(carry === 1)
        A.unshift(1);

    // Remove leading zeros
    while (A.length > 1 && A[0] === 0) {
        A.shift();
    }

    return A;
}
// Test cases
console.log(AddOneToNumber([1, 2, 3]));  // Should output [1, 2, 4]
console.log(AddOneToNumber([9, 9, 9]));  // Should output [1, 0, 0, 0]
console.log(AddOneToNumber([0, 1, 2, 3]));  // Should output [1, 2, 4]

// "node AddOneToNumber.js" in terminal