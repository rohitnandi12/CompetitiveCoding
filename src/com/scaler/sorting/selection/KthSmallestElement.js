function KthSmallestElement(A, B){
    for (let b = 0; b < B; b++) {
        let minEleIndex = b;
        for (let i = b + 1; i < A.length; i++) {
            if (A[i] < A[minEleIndex]) minEleIndex = i;
        }
        [A[b], A[minEleIndex]] = [A[minEleIndex], A[b]];
    }
    return A[B - 1];
}

console.log(KthSmallestElement([2, 1, 4, 3, 2], 3)) // 2
