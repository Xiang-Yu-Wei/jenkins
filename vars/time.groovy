def call() {
    def currentDateTime = java.time.LocalDateTime.now()

    // 格式化输出
    def formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    def formattedDateTime = currentDateTime.format(formatter)

    // 打印当前时间
    echo "当前时间是: ${formattedDateTime}"
}
