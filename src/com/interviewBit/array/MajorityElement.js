function MajorityElement(A){
    let potenitalMajorityElement = A[0];
    let count = 1;
    for(let i=1; i<A.length; i++) {
        // console.log(A[i]);
        if(A[i] == potenitalMajorityElement) {
            count += 1;
        } else {
            count -= 1;
            if(count === 0) {
                potenitalMajorityElement = A[i];
                count = 1;
            }
        }
    }
    count = 0;
    for(let i=0; i<A.length; i++) {
        if(A[i] === potenitalMajorityElement) {
            count += 1;
        }
    }

    return count > A.length / 2 ? potenitalMajorityElement : undefined;
};

console.log(MajorityElement([2, 1, 2, 3, 4]));
