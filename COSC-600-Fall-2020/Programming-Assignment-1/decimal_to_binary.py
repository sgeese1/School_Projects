def find(decimal_number):
    if decimal_number == 0:
        return 0
    else:
        return (decimal_number % 2 + 10 *
        find(int(decimal_number // 2)))

decimal_number = int(input("Please enter a decimal number: "))
print("The binary number is", find(decimal_number))