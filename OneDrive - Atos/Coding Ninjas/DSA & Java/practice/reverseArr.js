let arr = [1, 2, 3, 4, 5, 6];
let tmp;
for (let i = 0; i < arr.length - 1; i++) {
  tmp = arr[i + 1];
  arr[i + 1] = arr[i];
  arr[i] = tmp;
}
console.log(arr);
