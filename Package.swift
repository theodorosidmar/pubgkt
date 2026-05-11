// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "pubgkt",
    platforms: [
        .iOS(.v13),
        .watchOS(.v6),
    ],
    products: [
        .library(name: "pubgkt", targets: ["pubgkt"]),
    ],
    targets: [
        .binaryTarget(
            name: "pubgkt",
            url: "https://github.com/theodorosidmar/pubgkt/releases/download/v1.0.1/pubgkt.xcframework.zip",
            checksum: "2e5747cce427b310f60124e7d21f487aef77baf27032d56c381388efd0430f7b"
        ),
    ]
)

