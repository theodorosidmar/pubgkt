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
            url: "https://github.com/theodorosidmar/pubgkt/releases/download/v1.0.0/pubgkt.xcframework.zip",
            checksum: "0000000000000000000000000000000000000000000000000000000000000000"
        ),
    ]
)

