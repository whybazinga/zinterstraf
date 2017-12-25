let path = require('path');
let webpack = require('webpack');

let config = {
    entry: {
        'entry':'./src/entry.js'
    },
    output: {
        path: path.resolve(__dirname, '../webapp/resources/js/dist'),
        filename: "[name].js"
    },
    resolve: {
        extensions: ['.js'],
        alias: {
            'commonProject': path.resolve(__dirname, './template/commonJquery')
        }
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "window.jQuery": 'jquery',
            Popper: ['popper.js', 'default'],
            "window.Popper": ['popper.js', 'default'],
            commonProject: "commonProject"
        })
    ],
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['env']
                    }
                }
            },
            {
                test: /\.css$/,
                use: [ 'style-loader', 'css-loader' ]
            }
        ]
    }
};

module.exports = config;